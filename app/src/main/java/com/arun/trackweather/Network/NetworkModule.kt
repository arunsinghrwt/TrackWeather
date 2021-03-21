package com.arun.trackweather.Network

import androidx.multidex.BuildConfig
import com.arun.trackweather.Utility.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier


/**
// Created by Arun Singh Rawat  on 21-03-2021.



 **/

fun returnProvideRetrofit(): Retrofit {
    val logging = HttpLoggingInterceptor()

    if (BuildConfig.DEBUG) {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    } else {
        logging.setLevel(HttpLoggingInterceptor.Level.NONE)
    }
    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor(logging)
    val gson = GsonBuilder()
        .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
        .serializeNulls()
        .setLenient()
        .create()
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(
            httpClient.build()
        )
        .build()
}

fun getApi(retrofit: Retrofit): ApiInterface {
    return retrofit.create(ApiInterface::class.java)
}
