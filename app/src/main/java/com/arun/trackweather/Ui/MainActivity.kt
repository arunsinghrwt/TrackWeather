package com.arun.trackweather.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.FragmentManager
import com.arun.trackweather.R
import com.arun.trackweather.Utility.CheckIfGpsEnabled

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()


    }


    override fun onResume() {
        super.onResume()
        initView()
    }

    override fun onRestart() {
        super.onRestart()
        initView()
    }

    private fun initView() {
        if (!CheckIfGpsEnabled(this)) {
            android.util.Log.e("CheckIfGpsEnabled", "false")
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }
        else{
            var fm = supportFragmentManager.beginTransaction()
            fm.replace(R.id.container , HomeFragment.newInstance("",""))
                .commit()

        }




    }





}