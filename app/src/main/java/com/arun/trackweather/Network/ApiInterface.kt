package com.arun.trackweather.Network


import com.arun.trackweather.Model.ResponseModel
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


/**
// Created by Arun Singh Rawat  on 21-03-2021.



 **/

interface ApiInterface {

    //get weather detail from api
    @GET("/data/2.5/onecall")
    @Headers("Content-Type:application/json")
    fun fetchDetails(
            @Query("lat") lat: String?,
            @Query("lon") lon: String?,
            @Query("units") units: String?,
            @Query("exclude") exclude: String?,
            @Query("appid") appid: String?
    ): Flowable<ResponseModel>?

    @GET("/data/2.5/onecall")
    @Headers("Content-Type:application/json")
    fun sendOTP(  @Query("lat") lat: String?,
                  @Query("lon") lon: String?,
                  @Query("units") units: String?,
                  @Query("exclude") exclude: String?,
                  @Query("appid") appid: String?): Call<ResponseModel?>?


}