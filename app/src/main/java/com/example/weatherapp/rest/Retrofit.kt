package com.example.weatherapp.rest

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object Retrofit {
    //Provides the NetworkApi by retrofit, access using Retrofit.getNetworkApi()
    fun getNetworkApi():NetworkApi = run {
         return@run Retrofit.Builder()
             .baseUrl(NetworkApi.BASE_URL)
             .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
             .addConverterFactory(MoshiConverterFactory.create(
                 Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
             ))
             .build()
             .create(NetworkApi::class.java)
    }
}