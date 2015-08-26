package com.demo.rxjavaexample.app;

import rx.Observable;
import rx.android.app.support.RxFragment;
import rx.android.lifecycle.LifecycleObservable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Kros on 8/25/15.
 */
public class BaseFragment extends RxFragment {
    protected <T> Observable<T> bind(Observable<T> observable) {
        return LifecycleObservable.bindFragmentLifecycle(lifecycle(),
                observable.observeOn(AndroidSchedulers.mainThread()));
    }
}
