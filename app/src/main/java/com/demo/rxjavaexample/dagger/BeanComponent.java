package com.demo.rxjavaexample.dagger;

import com.demo.rxjavaexample.MainActivity;
import com.demo.rxjavaexample.MainActivityFragment;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Created by Kros on 8/25/15.
 */
@Singleton
@Component(modules = {NetworkModule.class})
public interface BeanComponent {
    void inject(MainActivity activity);

    void inject(MainActivityFragment fragment);
}
