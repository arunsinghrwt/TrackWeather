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
import java.text.SimpleDateFormat
import java.util.*


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
        val sdf = SimpleDateFormat("hh:mm a")
        val netDate = Date(item.dt.toLong()* 1000)
        val date =sdf.format(netDate)
        holder.itemView.time.text = date
        holder.itemView.weather_desc.text = item.weather[0].main
        holder.itemView.temp.text = item.temp.toString()+"\u2103"
        when(item.weather[0].main){

           "Clouds" ->{
               holder.itemView.weather_icon.setImageResource(R.drawable.cloudy)
           }
            "Clear" ->{
                holder.itemView.weather_icon.setImageResource(R.drawable.ic_outline_wb_sunny_24)
            }
            "Rain" ->{
                holder.itemView.weather_icon.setImageResource(R.drawable.rain)
            }



        }



    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)




}