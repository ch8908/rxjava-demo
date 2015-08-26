package com.demo.rxjavaexample;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.demo.rxjavaexample.app.BaseFragment;
import com.demo.rxjavaexample.app.DemoApplication;
import com.demo.rxjavaexample.model.ApiResponse;
import com.demo.rxjavaexample.model.Toilet;
import com.demo.rxjavaexample.retrofit.ApiService;
import com.demo.rxjavaexample.view.ToiletAdapter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends BaseFragment {


    public static final String RID = "199a519c-5113-4e40-b00b-22a54ecf6d70";
    public static final String SCOPE = "resourceAquire";

    public MainActivityFragment() {
    }

    @Inject ApiService apiService;
    @Bind(R.id.recyclerView) RecyclerView recyclerView;
    @Bind(R.id.progressBar) ProgressBar progressBar;

    private ToiletAdapter adapter;
    private Location myLocation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        DemoApplication.getInstance().getComponent().inject(this);

        myLocation = new Location("Me");
        myLocation.setLatitude(25.043493);
        myLocation.setLongitude(121.557214);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ToiletAdapter(this, myLocation);
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar.setVisibility(View.VISIBLE);
        bind(fetchNearestToilet())
                .finallyDo(() -> progressBar.setVisibility(View.GONE))
                .subscribe(adapter::reset,
                        throwable -> ViewHelper.showError(getActivity(), throwable));
//        fetchWithCallback();
    }

    private void fetchWithCallback() {
        apiService.listToiletCallback(RID, SCOPE, 500, 0, new Callback<ApiResponse>() {
            @Override
            public void success(ApiResponse apiResponse, Response response) {
                List<Toilet> filtered = new ArrayList<>();
                for (Toilet toilet : apiResponse.getResult().getToilets()) {
                    if (lessThan5Km(toilet)) {
                        filtered.add(toilet);
                    }
                }
                Collections.sort(filtered, new Comparator<Toilet>() {
                    @Override
                    public int compare(Toilet lhs, Toilet rhs) {
                        return compareDistance(lhs, rhs);
                    }
                });
                adapter.reset(filtered);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void failure(RetrofitError error) {
                progressBar.setVisibility(View.GONE);
                ViewHelper.showError(getActivity(), error);
            }
        });
    }

    private Observable<List<Toilet>> fetchNearestToilet() {
        return apiService.listToilet(RID, SCOPE, 500, 0)
                .flatMap(response -> Observable.from(response.getResult().getToilets()))
                .filter(this::lessThan5Km)
                .toSortedList(this::compareDistance);
    }

    private Boolean lessThan5Km(Toilet toilet) {
        Location target = new Location(toilet.getId());
        target.setLatitude(Double.valueOf(toilet.getLat()));
        target.setLongitude(Double.valueOf(toilet.getLng()));
        float distanceTo = myLocation.distanceTo(target);
        return distanceTo < 5000;
    }

    private int compareDistance(Toilet t1, Toilet t2) {
        Location t1Location = new Location("t1");
        t1Location.setLatitude(Double.valueOf(t1.getLat()));
        t1Location.setLongitude(Double.valueOf(t1.getLng()));
        float t1Distance = myLocation.distanceTo(t1Location);

        Location t2Location = new Location("t2");
        t2Location.setLatitude(Double.valueOf(t2.getLat()));
        t2Location.setLongitude(Double.valueOf(t2.getLng()));
        float t2Distance = myLocation.distanceTo(t2Location);
        return Float.compare(t1Distance, t2Distance);
    }
}
