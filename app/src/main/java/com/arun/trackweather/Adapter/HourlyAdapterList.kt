package com.arun.trackweather.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arun.trackweather.Model.DailyList
import com.arun.trackweather.Model.HourlyList
import com.arun.trackweather.R
import kotlinx.android.synthetic.main.daily_raw_layout.view.*
import kotlinx.android.synthetic.main.hourly_raw_layout.view.*
import java.util.ArrayList


/**
 *Created by Arun Singh RAwat  on 20-03-2021.
 *https://github.com/arunsinghrwt

 */
class HourlyAdapterList(var itemList: ArrayList<HourlyList>, var context: Context) : RecyclerView.Adapter<HourlyAdapterList.ViewHolder> (){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLayoutView = LayoutInflater.from(context)
                .inflate(R.layout.hourly_raw_layout, null)
        return ViewHolder(itemLayoutView)
    }
    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.itemView.time.text = item.dt.toString()
        holder.itemView.weather_desc.text = item.weather[0].main
        holder.itemView.temp.text = item.temp.toString()



    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)




}