package com.arun.trackweather.Model

import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class Current : Serializable {
    var dt : Int = 0
    var sunrise : Int = 0
    var sunset  : Int = 0
    var temp : Double = 0.0
    var feels_like : Double = 0.0
    var pressure : Int = 0
    var humidity : Int = 0
    var dew_point: Double = 0.0
    var uvi : Double = 0.0
    var clouds : Int = 0
    var visibility : Int = 0
    var wind_speed : Double = 0.0
    var wind_deg : Int = 0
    var weather  = ArrayList<Weather>()


}



class Weather  {
    var id  : Int = 0
    var main  : String = ""
    var description  : String = ""
    var icon  : String = ""
}