package com.demo.rxjavaexample.app;

/**
 * Created by Kros on 8/25/15.
 */
public class ApiConfiguration {
    private final String endPoint;

    public ApiConfiguration(String endPoint) {
        this.endPoint = endPoint;
    }

    public String baseDataApiUrlString() {
        return endPoint;
    }
}
