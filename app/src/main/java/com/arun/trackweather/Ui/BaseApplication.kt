package com.arun.trackweather.Ui

import androidx.multidex.MultiDexApplication
import com.arun.trackweather.Network.ApiInterface
import com.arun.trackweather.Network.retrofitModule
import com.arun.trackweather.Utility.Constants
import com.fxn.stash.Stash
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
// Created by Arun Singh Rawat  on 21-03-2021.



 **/

class BaseApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        Stash.init(applicationContext)
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            koin.loadModules(listOf(retrofitModule))
            koin.createRootScope()



        }

        data = getNewClient(Constants.BASE_URL)!!.create(ApiInterface::class.java)

    }


    companion object{

        var data : ApiInterface? = null
        private var newOtpRetrofit : Retrofit? = null
        fun getNewClient(BaseUrl: String?): Retrofit? {
            if (newOtpRetrofit == null) {
                newOtpRetrofit = Retrofit.Builder()
                        .baseUrl(BaseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return newOtpRetrofit
        }
    }



}