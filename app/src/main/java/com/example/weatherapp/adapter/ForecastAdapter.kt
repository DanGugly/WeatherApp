package com.example.weatherapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.model.CityForecast
import com.example.weatherapp.model.Forecast
import com.squareup.picasso.Picasso
import java.text.DateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ForecastAdapter(
    private val forecastDetailsClick: ForecastDetailsClick,
    private var city : String?,
    private val forecastList:MutableList<Forecast> = mutableListOf()

) : RecyclerView.Adapter<ForecastViewHolder>(){

    fun updateForecast(cityForecast: CityForecast){
        //Remove previous list, e.g enter atlanta, hit back, enter orlando orlando list will still have atl if we don't clear
        forecastList.clear()
        city = cityForecast.city.name
        forecastList.addAll(cityForecast.list)
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
        holder.forecastCity.text = city
        holder.forecastTemp.text = convertKelToCelsiusString(forecast.main.temp)
        holder.forecastMinTemp.text = convertKelToCelsiusString(forecast.main.tempMin)
        holder.forecastMaxTemp.text = convertKelToCelsiusString(forecast.main.tempMax)
        holder.forecastFeelsTemp.text = feelsApend+convertKelToCelsiusString(forecast.main.feelsLike)
        holder.forecastDateTime.text = dateFormat(forecast.dtTxt)
        holder.forecastWeatherDesc.text = forecast.weather[primaryWeather].description
        getWeatherIcon(forecast.weather[primaryWeather].icon,holder.forecastWeatherIcon)
        //holder.forecastWeatherIcon.setImageBitmap(forecast.weather[weatherIcon].toString())
        holder.itemView.setOnClickListener {
            city?.let { it1 -> forecastDetailsClick.moveToForecastDetails(it1,forecast) }
        }
    }

    override fun getItemCount(): Int = forecastList.size

    private fun convertKelToCelsiusString(temp: Double):String{
        return (String.format("%.1f",temp - celsius).toDouble()).toString()+ TEMP_UNIT
    }

    private fun dateFormat(date:String):String{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val formatterOut = DateTimeFormatter.ofPattern("d MMM H:ma")
        val dateFinal = LocalDateTime.parse(date,formatter)
        return dateFinal.format(formatterOut)
    }

    private fun getWeatherIcon(path:String, weather: ImageView){
        Log.d("WEATHICON",path)
        Picasso
            .get()
            .load(IMAGE_URL+path+ IMAGE_URL_APPEND_x2)
            .resize(250,250)
            //.centerCrop()
            .into(weather)
    }

    companion object{
        val formatter = DateTimeFormatter.ofPattern("d MMM h:ma")
        private const val IMAGE_URL = "http://openweathermap.org/img/wn/"
        private const val TEMP_UNIT = "°C"
        private const val IMAGE_URL_APPEND_x2 = "@2x.png"
        private const val IMAGE_URL_APPEND_x4 = "@4x.png"
        private const val feelsApend:String = "Feels like "
        private const val primaryWeather : Int = 0
        private const val celsius:Double = 273.15
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
    val forecastWeatherIcon : ImageView = itemView.findViewById(R.id.weather_icon)
}