package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.model.Forecast

class ForecastAdapter(
    private val forecastList:MutableList<Forecast> = mutableListOf()
) : RecyclerView.Adapter<ForecastViewHolder>(){

    fun updateForecast(newForecast: List<Forecast>){
        //Remove previous list, e.g enter atlanta, hit back, enter orlando orlando list will still have atl if we don't clear
        forecastList.clear()
        forecastList.addAll(newForecast)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val forecastView = LayoutInflater.from(parent.context).inflate(
            R.layout.forecast_items,
            parent,
            false
        )
        return ForecastViewHolder(forecastView)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = forecastList.size

}
class ForecastViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

}