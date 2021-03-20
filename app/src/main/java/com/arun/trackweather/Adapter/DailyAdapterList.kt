package com.arun.trackweather.Adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList


/**
 *Created by Arun Singh Rawat on 20-03-2021.
 *https://github.com/arunsinghrwt

 */
class DailyAdapterList(var itemList: ArrayList<Int> ,var context: Context) : RecyclerView.Adapter<DailyAdapterList.ViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyAdapterList.ViewHolder {

    }

    override fun onBindViewHolder(holder: DailyAdapterList.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {

    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}