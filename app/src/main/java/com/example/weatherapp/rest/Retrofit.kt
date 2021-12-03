package com.example.weatherapp.rest

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object Retrofit {
    //This okHttpClient allows us to set headers and interceptors to the request & response
    private val okHttpClient by lazy{
        OkHttpClient.Builder()
                //Here interceptor for logging is being added
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                //Timeout for retrieving data from GET request
            .readTimeout(30, TimeUnit.SECONDS)
                //Timeout for writing data from POST and PUT
            .writeTimeout(30, TimeUnit.SECONDS)
                //Timeout for connection to service
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
        //Can add another intercepter here if you want
    }
    //Provides the NetworkApi by retrofit, access using Retrofit.getNetworkApi()
    fun getNetworkApi():NetworkApi = run {
         return@run Retrofit.Builder()
             .baseUrl(NetworkApi.BASE_URL)
             .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
             .addConverterFactory(MoshiConverterFactory.create(
                 Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
             ))
             .client(okHttpClient)
             .build()
             .create(NetworkApi::class.java)
    }
}