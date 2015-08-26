package com.demo.rxjavaexample.retrofit;

import com.demo.rxjavaexample.model.ApiResponse;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Kros on 8/25/15.
 */
public interface ApiService {

    @GET("/apiAccess")
    Observable<ApiResponse> listToilet(@Query("rid") String rid, @Query("scope") String scope, @Query("limit") int limit, @Query("offset") int offset);
}
