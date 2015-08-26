package com.demo.rxjavaexample.retrofit;

import com.demo.rxjavaexample.model.ApiResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Kros on 8/25/15.
 */
public interface ApiService {

    // - 跟 Server 抓取公廁資料
    @GET("/apiAccess")
    Observable<ApiResponse> listToilet(@Query("rid") String rid,
                                       @Query("scope") String scope,
                                       @Query("limit") int limit,
                                       @Query("offset") int offset);

    @GET("/apiAccess")
    void listToiletCallback(@Query("rid") String rid,
                            @Query("scope") String scope,
                            @Query("limit") int limit,
                            @Query("offset") int offset,
                            Callback<ApiResponse> callback);
}
