package com.example.weatherapp.adapter

import com.example.weatherapp.model.Forecast

interface ForecastDetailsClick {
    fun moveToForecastDetails(cityName:String, forecast:Forecast)
}