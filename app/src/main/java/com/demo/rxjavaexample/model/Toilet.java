package com.demo.rxjavaexample.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kros on 8/25/15.
 */
public class Toilet {
    @SerializedName("_id")
    String id;

    @SerializedName("unit")
    String unit;

    @SerializedName("title")
    String title;

    @SerializedName("lng")
    String lng;

    @SerializedName("lat")
    String lat;

    @SerializedName("address")
    String address;

    public String getId() {
        return id;
    }

    public String getUnit() {
        return unit;
    }

    public String getTitle() {
        return title;
    }

    public String getLng() {
        return lng;
    }

    public String getLat() {
        return lat;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Toilet toilet = (Toilet) o;

        return !(id != null ? !id.equals(toilet.id) : toilet.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Toilet{" +
                "id='" + id + '\'' +
                ", unit='" + unit + '\'' +
                ", title='" + title + '\'' +
                ", lng=" + lng +
                ", lat=" + lat +
                ", address='" + address + '\'' +
                '}';
    }
}
