package com.demo.rxjavaexample;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import com.demo.rxjavaexample.app.BaseActivity;
import com.demo.rxjavaexample.app.DemoApplication;
import com.demo.rxjavaexample.model.Toilet;
import com.demo.rxjavaexample.retrofit.ApiService;
import rx.Observable;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    @Inject ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DemoApplication.getInstance().getComponent().inject(this);
    }

    public void list() {
        Observable.just(1, 2, 3, 4, 5, 6, 7).map(integer -> {
            System.out.println(">>>>>>>>>>>>>>>>>>> integer:" + integer);
            return integer;
        }).subscribe();

        Observable.just("Hello World!").map(s -> s + " Android Taipei")
                .subscribe(s -> {
                    System.out.println(">>>>>>>>>>>>>>>>>>> s:" + s);
                });

        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        integers.add(7);
        // ...
        Observable.from(integers).map(integer -> {
            System.out.println(">>>>>>>>>>>>>>>>>>> integer:" + integer);
            return integer;
        }).subscribe();
    }
}
