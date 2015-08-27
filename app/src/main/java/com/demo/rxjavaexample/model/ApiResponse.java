package com.demo.rxjavaexample.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kros on 8/25/15.
 */
public class ApiResponse {
    @SerializedName("result")
    private final Result result;

    public ApiResponse(Result result) {this.result = result;}

    public Result getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "result=" + result +
                '}';
    }
}
