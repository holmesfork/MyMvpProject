package com.example.lenovo.nettest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @作者：zhaojunkai
 * @时间：2017/6/9 14:37
 */
public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://result.eolinker.com/";
    private static final String TAG = "abc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())


                .build();

       retrofit.create(MovieService.class)
               .getTop("my")
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Consumer<MovieSubject>() {
                   @Override
                   public void accept(@NonNull MovieSubject movieSubject) throws Exception {
                       Log.d(TAG, "accept: "+movieSubject.toString());
                   }
               });




//        //获取接口实例   源生Retrofit请求数据
//        MovieService movieService = retrofit.create(MovieService.class);
//        //调用方法得到一个Call
//        Call<MovieSubject> call = movieService.getTop250("my");
//        //进行网络请求
//        call.enqueue(new Callback<MovieSubject>() {
//            @Override
//            public void onResponse(Call<MovieSubject> call, Response<MovieSubject> response) {
////
//                Log.d(TAG, "onResponse: " + response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<MovieSubject> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
    }
}
