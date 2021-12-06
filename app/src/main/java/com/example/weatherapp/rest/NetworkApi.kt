package com.example.weatherapp.rest

import com.example.weatherapp.model.CityForecast
import io.reactivex.Completable
import io.reactivex.Single
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
    //Return a Single of CityForecast, single as we only get 1 value not an Observable
    ):Single<CityForecast>
    //): Completable
    //Completable represent Observable that emits no value, but only terminal events, either onError or onCompleted'
    //Observable : emitting data continuously() with constant updates from API
    //Flowable : same as observable but will handle backpressure(Where data generated faster than it can be processed)
    //Maybe: will return the tpe of data and also a completable action(onSuccess, onError, onComplete)
    // combination between Single and Completable

    //cold observable : Data will be emitted within the observable instantiation
    //hot observable : produce data outside observable, so when observed you already have data that will be emitted

    companion object{
        //Set API Key
        private const val API_KEY = "65d00499677e59496ca2f318eb68c049"
        //Weather forecast path
        private const val WEATHER_FORECAST = "data/2.5/forecast"
        //Finding our base URL
        const val BASE_URL = "https://api.openweathermap.org/"
    }
}