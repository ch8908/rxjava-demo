package com.demo.rxjavaexample.app;

import android.app.Application;
import com.demo.rxjavaexample.dagger.BeanComponent;
import com.demo.rxjavaexample.dagger.DaggerBeanComponent;
import com.demo.rxjavaexample.dagger.NetworkModule;

/**
 * Created by Kros on 8/25/15.
 */
public class DemoApplication extends Application {
    private static DemoApplication INSTANCE;

    private BeanComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        component = DaggerBeanComponent.builder()
                .networkModule(new NetworkModule(this, "http://data.taipei/opendata/datalist"))
                .build();
    }

    public static DemoApplication getInstance() {
        return INSTANCE;
    }

    public BeanComponent getComponent() {
        return component;
    }
}
