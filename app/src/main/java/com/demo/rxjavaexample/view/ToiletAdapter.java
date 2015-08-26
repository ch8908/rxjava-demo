package com.demo.rxjavaexample.view;

import android.location.Location;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.demo.rxjavaexample.R;
import com.demo.rxjavaexample.model.Toilet;
import com.demo.rxjavaexample.view.ToiletAdapter.ToiletViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wish8 on 8/26/15.
 */
public class ToiletAdapter extends RecyclerView.Adapter<ToiletViewHolder> {

    public static class ToiletViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.idTextView) TextView idTextView;
        @Bind(R.id.nameTextView) TextView nameTextView;
        @Bind(R.id.distanceTextView) TextView distanceTextView;

        public ToiletViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private List<Toilet> toilets = new ArrayList<>();
    private final Fragment fragment;
    private Location myLocation;

    public ToiletAdapter(Fragment fragment, Location myLocation) {
        this.fragment = fragment;
        this.myLocation = myLocation;
    }

    @Override
    public ToiletViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_toilet, viewGroup, false);
        return new ToiletViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ToiletViewHolder toiletViewHolder, int i) {
        Toilet toilet = toilets.get(i);
        toiletViewHolder.idTextView.setText(fragment.getString(R.string.toilet_id, toilet.getId()));
        toiletViewHolder.nameTextView.setText(toilet.getTitle());
        Location target = new Location(toilet.getId());
        target.setLatitude(Double.valueOf(toilet.getLat()));
        target.setLongitude(Double.valueOf(toilet.getLng()));
        toiletViewHolder.distanceTextView.setText(fragment.getString(R.string.distance_away, myLocation.distanceTo(target)));
    }

    @Override
    public int getItemCount() {
        return toilets.size();
    }

    public void reset(List<Toilet> toilets) {
        this.toilets.clear();
        this.toilets.addAll(toilets);
        notifyDataSetChanged();
    }

}
