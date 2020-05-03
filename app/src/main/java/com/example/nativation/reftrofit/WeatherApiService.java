package com.example.nativation.reftrofit;

import com.example.nativation.reftrofit.http.GET;
import com.example.nativation.reftrofit.http.Query;

import okhttp3.Call;


/**
 * 作者：Create on 2020/3/28 22:58  by  wzl
 * 描述：
 * 最近修改：2020/3/28 22:58 modify by wzl
 */
public interface WeatherApiService {


    @GET("weather/index")
    Call getweather(@Query("format") int format,
                                 @Query("cityname")String city,
                                 @Query("key")String key
    );


}