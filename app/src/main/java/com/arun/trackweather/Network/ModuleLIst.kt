package com.arun.trackweather.Network

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
// Created by Arun Singh Rawat  on 21-03-2021.



 **/

 var retrofitModule  = module {
    single { returnProvideRetrofit() }
    single { getApi(get()) }
    viewModel { ViewModelWeather(get ()) }


}