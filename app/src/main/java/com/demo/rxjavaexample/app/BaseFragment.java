package com.demo.rxjavaexample.app;

import com.trello.rxlifecycle.components.support.RxFragment;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Kros on 8/25/15.
 */
public class BaseFragment extends RxFragment {
    protected <T> Observable<T> bind(Observable<T> observable) {
        return observable.compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
