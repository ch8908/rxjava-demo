package com.demo.rxjavaexample;

import android.location.Location;
import android.os.Bundle;
import com.demo.rxjavaexample.app.BaseActivity;
import com.demo.rxjavaexample.app.DemoApplication;
import com.demo.rxjavaexample.model.Toilet;
import com.demo.rxjavaexample.retrofit.ApiService;
import rx.Observable;

import javax.inject.Inject;
import java.util.List;

public class MainActivity extends BaseActivity {

    @Inject ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DemoApplication.getInstance().getComponent().inject(this);
        bind(fetchNearestToilet()).subscribe(toilets -> {
            System.out.println(">>>>>>>>>>>>>>>>>>> toilets:" + toilets);
        });
    }

    private Observable<List<Toilet>> fetchNearestToilet() {
        return apiService.listToilet("199a519c-5113-4e40-b00b-22a54ecf6d70", "resourceAquire", 500, 0)
                .flatMap(apiResponse -> Observable.from(apiResponse.getResult().getToilets()))
                .filter(this::lessThan5Km)
                .toSortedList(this::compareDistance);
    }

    private Boolean lessThan5Km(Toilet toilet) {
        Location myLocation = new Location("Me");
        myLocation.setLatitude(25.0182382);
        myLocation.setLongitude(121.5326403);
        Location target = new Location("toilet");
        target.setLatitude(Double.valueOf(toilet.getLat()));
        target.setLongitude(Double.valueOf(toilet.getLng()));
        float distanceTo = myLocation.distanceTo(target);
        return distanceTo < 5000;
    }

    private int compareDistance(Toilet t1, Toilet t2) {
        Location myLocation = new Location("Me");
        myLocation.setLatitude(25.0182382);
        myLocation.setLongitude(121.5326403);

        Location t1Location = new Location("t1");
        t1Location.setLatitude(Double.valueOf(t1.getLat()));
        t1Location.setLongitude(Double.valueOf(t1.getLng()));
        float t1Distance = myLocation.distanceTo(t1Location);

        Location t2Location = new Location("t2");
        t2Location.setLatitude(Double.valueOf(t2.getLat()));
        t2Location.setLongitude(Double.valueOf(t2.getLng()));
        float t2Distance = myLocation.distanceTo(t1Location);

        return Float.compare(t2Distance, t1Distance);
    }
}
