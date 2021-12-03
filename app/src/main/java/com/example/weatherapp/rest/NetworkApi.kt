package com.example.weatherapp.rest

import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {
    //Don't need entire path as we want a dynamic query
    // (https://api.openweathermap.org/data/2.5/forecast?q=atlanta&appid=65d00499677e59496ca2f318eb68c049)
    // q is for city and appid is API key
    @GET(WEATHER_FORECAST)
    //Doing a query passing exact name in the parenthesis
    fun getForecast(
        @Query("q") cityName:String,
        //Assign value directly as it's a static value
        @Query("appid") apiKey:String = API_KEY
    ){

    }
    companion object{
        //Set API Key
        private const val API_KEY = "65d00499677e59496ca2f318eb68c049"
        //Weather forecast path
        private const val WEATHER_FORECAST = "data/2.5/forecast"
    }
}