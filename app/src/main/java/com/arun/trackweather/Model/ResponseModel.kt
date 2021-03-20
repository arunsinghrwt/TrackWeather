package com.arun.trackweather.Model

import java.io.Serializable


/**
 *Created by Arun Singh Rawat  on 20-03-2021.
 *https://github.com/arunsinghrwt

 */
class ResponseModel : Serializable{
    var lat : Float = 0F
    var lon : Float = 0F
    var timezone : String = ""
    var current  = Current()
    var hourly = ArrayList<Current>()
    var daily = ArrayList<DailyList>()

}