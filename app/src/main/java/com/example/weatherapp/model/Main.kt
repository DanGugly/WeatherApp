package com.example.weatherapp.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Main(
    @Json(name = "feels_like")
    var feelsLike: Double,
    @Json(name = "grnd_level")
    val grndLevel: Int,
    @Json(name = "humidity")
    val humidity: Int,
    @Json(name = "pressure")
    val pressure: Int,
    @Json(name = "sea_level")
    val seaLevel: Int,
    @Json(name = "temp")
    var temp: Double,
    @Json(name = "temp_kf")
    val tempKf: Double,
    @Json(name = "temp_max")
    var tempMax: Double,
    @Json(name = "temp_min")
    var tempMin: Double
)