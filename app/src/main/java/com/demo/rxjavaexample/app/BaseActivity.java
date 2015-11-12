package com.demo.rxjavaexample.app;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Kros on 8/25/15.
 */
public class BaseActivity extends RxAppCompatActivity {

    protected <T> Observable<T> bind(Observable<T> observable) {
        return observable.compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
