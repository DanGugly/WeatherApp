package com.example.weatherapp

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.weatherapp.adapter.ForecastAdapter
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.model.CityForecast
import com.example.weatherapp.model.Forecast
import com.example.weatherapp.rest.Retrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()
        binding.getForecast.setOnClickListener{
            if (checkEmptyValues()){
                Toast.makeText(baseContext,"Please enter city :)", Toast.LENGTH_LONG).show()
            }
            else{
                Intent(baseContext,ForecastActivity::class.java).apply {
                    putExtra(CITY_DATA, binding.cityName.editText?.text.toString())
                    startActivity(this)
                }
            }
        }
    }

    private fun checkEmptyValues(): Boolean = binding.getForecast.text.toString().isEmpty()
    companion object{
        const val CITY_DATA = "CITY_DATA"
    }
}
