package com.arun.trackweather.Network

import android.util.Log
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.arun.trackweather.Model.ResponseModel
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.schedulers.Schedulers


/**
// Created by Arun Singh rawat on 21-03-2021.



 **/

class ViewModelWeather(var apiInterface: ApiInterface) : ViewModel() {

    var mediatorList = MediatorLiveData<ResponseModel>()
    fun getWeatherDetailList(){

        var list = LiveDataReactiveStreams.fromPublisher(
            apiInterface.fetchDetails("26.835251","75.824341","metric","minutely","062017fedd4d2aea77a59490174aa31e")!!.onErrorReturn {
              ResponseModel().apply {
                  Log.e("error","--->>"+it.cause + it.message)
              }
            }.map {
                return@map try {
                    Log.e("getWeatherDetailList","->> "+ GsonBuilder().create().toJson(it))
                    it
                } catch (e: Exception) {
                    it

                }
            }.subscribeOn(Schedulers.io())
        )

        mediatorList.addSource(list){
                t ->
            mediatorList.value = t
            mediatorList.removeSource(list)

        } }

}