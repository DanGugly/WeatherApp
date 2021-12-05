package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
        val forecast = forecastList[position]
        holder.forecastCity.text = "Atlanta"
        holder.forecastTemp.text = forecast.main.temp.toString()
        holder.forecastMinTemp.text = forecast.main.tempMin.toString()
        holder.forecastMaxTemp.text = forecast.main.tempMax.toString()
        holder.forecastFeelsTemp.text = forecast.main.feelsLike.toString()
        holder.forecastDateTime.text = forecast.dtTxt
        holder.forecastWeatherDesc.text = forecast.weather[weatherDesc].toString()
        //holder.forecastWeatherIcon.setImageBitmap(forecast.weather[weatherIcon].toString())
    }

    override fun getItemCount(): Int = forecastList.size

    companion object{
        private val weatherDesc : Int = 0
        private val weatherIcon : Int = 1
        private val weatherId : Int = 2
        private val weatherMain : Int = 3
    }
}
class ForecastViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
    val forecastCity : TextView = itemView.findViewById(R.id.forecast_city)
    val forecastTemp : TextView = itemView.findViewById(R.id.forecast_temp)
    val forecastMinTemp : TextView = itemView.findViewById(R.id.forecast_mintemp)
    val forecastMaxTemp : TextView = itemView.findViewById(R.id.forecast_maxtemp)
    val forecastFeelsTemp : TextView = itemView.findViewById(R.id.forecast_feelstemp)
    val forecastDateTime : TextView = itemView.findViewById(R.id.forecast_datetime)
    val forecastWeatherDesc : TextView = itemView.findViewById(R.id.weather_description)
    //val forecastWeatherIcon : ImageView = itemView.findViewById(R.id.weather_icon)
}