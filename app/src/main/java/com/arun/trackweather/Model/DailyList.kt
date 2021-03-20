package com.arun.trackweather.Model

import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class DailyList : Serializable {
    var dt : Date? = null
    var sunrise : Date? = null
    var sunset : Date? = null
    var temp = DailyTemp()
    var feels_like  = DailyFeelsLike()
    var pressure : Int? = null
    var humidity : Int? = null
    var dew_point : Float? = null
    var uvi : Int? = null
    var clouds : Int? = null
    var wind_speed : Float? = null
    var wind_deg : Int? = null
    var pop : Int? = 0
    var rain : Float?  =0F
    var weather  = ArrayList<DailyWeather>()


}

class DailyFeelsLike {
    var day : Float? = null
    var night : Float? = null
    var eve : Float? = null
    var morn : Float? = null
}

class DailyTemp : Serializable {
    var day : Float? = null
    var min : Float? = null
    var max : Float? = null
    var night : Float? = null
    var eve : Float? = null
    var morn : Float? = null

}


class DailyWeather : Serializable {
    var id : Int? = null
    var main : String? = null
    var description : String? = null
    var icon : String? = null

}
