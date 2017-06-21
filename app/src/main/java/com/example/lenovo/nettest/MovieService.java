package com.example.lenovo.nettest;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lenovo on 2017/6/9.
 */

public interface MovieService {


    @GET("Kn5mQ5bc3f37907192691b123841b88fd19ed7dfdb7d55c")
    Observable<MovieSubject> getTop(@Query("uri") String start);
}
