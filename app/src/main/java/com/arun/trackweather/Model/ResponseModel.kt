package com.arun.trackweather.Model



/**
 *Created by Arun Singh Rawat  on 20-03-2021.
 *https://github.com/arunsinghrwt

 */
class ResponseModel {
    var lat : Double = 0.0
    var lon : Double  = 0.0
    var timezone : String = ""
    var timezone_offset : Int = 0
    var current = Current()
    var hourly : ArrayList<HourlyList>? = null
    var daily : ArrayList<DailyList>? = null
}