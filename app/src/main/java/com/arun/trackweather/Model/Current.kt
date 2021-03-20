package com.arun.trackweather.Model

import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class Current : Serializable {
    var dt : Date? = null
    var sunrise : Date? = null
    var sunset : Date? = null
    var temp : Float? = null
    var feels_like : Float? = null
    var pressure : Int? = null
    var humidity : Int? = null
    var dew_point : Float? = null
    var uvi : Int? = null
    var clouds : Int? = null
    var visibility : Int? = null
    var wind_speed : Float? = null
    var wind_deg : Int? = null
    var weather  = ArrayList<Weather>()


}



class Weather : Serializable {
    var id : Int? = null
    var main : String? = null
    var description : String? = null
    var icon : String? = null

}