package com.arun.trackweather.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.arun.trackweather.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()


    }

    private fun initView() {

                var fm = supportFragmentManager.beginTransaction()
                fm.replace(R.id.container , HomeFragment.newInstance("",""))
                    .commit()



    }
}