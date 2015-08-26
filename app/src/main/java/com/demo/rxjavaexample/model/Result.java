package com.demo.rxjavaexample.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kros on 8/25/15.
 */
public class Result {
    @SerializedName("offset")
    int offset;

    @SerializedName("limit")
    int limit;

    @SerializedName("count")
    int count;

    @SerializedName("results")
    List<Toilet> toilets;

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getCount() {
        return count;
    }

    public List<Toilet> getToilets() {
        return toilets;
    }

    @Override
    public String toString() {
        return "Result{" +
                "offset=" + offset +
                ", limit=" + limit +
                ", count=" + count +
                ", toilets=" + toilets +
                '}';
    }
}
