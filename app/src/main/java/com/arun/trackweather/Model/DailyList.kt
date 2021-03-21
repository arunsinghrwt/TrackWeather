package com.arun.trackweather.Model

import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class DailyList : Serializable {
    var dt : Int = 0
    var sunrise : Int = 0
    var sunset : Int = 0
    var temp = DailyTemp()
    var feels_like  = DailyFeelsLike()
    var pressure : Int = 0
    var humidity : Int = 0
    var dew_point : Double = 0.0
    var uvi : Double = 0.0
    var clouds  : Int = 0
    var wind_speed : Double = 0.0
    var wind_deg : Int = 0
    var pop  : Double = 0.0
    var weather  : ArrayList<DailyWeather> ?= null


}

class DailyFeelsLike {
    var day : Double = 0.0
    var night : Double = 0.0
    var eve: Double = 0.0
    var morn : Double = 0.0
}

class DailyTemp : Serializable {
    var day : Double = 0.0
    var min : Double = 0.0
    var max : Double = 0.0
    var night : Double = 0.0
    var eve : Double = 0.0
    var morn : Double = 0.0

}


class DailyWeather : Serializable {
    var id : Int = 0
    var main : String= ""
    var description : String = ""
    var icon : String = ""

}
