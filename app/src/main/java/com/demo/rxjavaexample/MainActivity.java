package com.demo.rxjavaexample;

import android.os.Bundle;
import com.demo.rxjavaexample.app.BaseActivity;
import com.demo.rxjavaexample.app.DemoApplication;
import com.demo.rxjavaexample.retrofit.ApiService;
import rx.Observable;
import rx.functions.Action1;

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
        practiceRxJava();
    }

    public void practiceRxJava() {
        Observable.just("Hello World!").map(s -> s + " Android Taipei")
                .subscribe(s -> {
                    System.out.println(">>>>>>>>>>>>>>>>>>> s:" + s);
                });

        Observable.just("Hello World!").subscribe(s -> {
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

        Observable.from(integers)
                .map(integer -> integer + 10)
                .subscribe(integer -> {
                    System.out.println(">>>>>>>>>>>>>>>>>>> integer:" + integer);
                });

        Observable.from(integers).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("111>>>>>>>>>>>>>>>>>>> integer:" + integer);
            }
        });
    }
}
