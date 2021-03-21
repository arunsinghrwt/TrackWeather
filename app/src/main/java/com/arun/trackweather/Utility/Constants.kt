package com.arun.trackweather.Utility

import android.content.Context
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity


/**
// Created by Arun Singh Rawat  on 21-03-2021.



 **/
object Constants{
    val BASE_URL = "https://api.openweathermap.org"
    val LAT = "LAT"
    val LNG = "LNG"


}

fun CheckIfGpsEnabled(appCompatActivity: AppCompatActivity): Boolean {
    return (appCompatActivity.getSystemService(Context.LOCATION_SERVICE) as LocationManager).isProviderEnabled(
        LocationManager.GPS_PROVIDER
    )
}
