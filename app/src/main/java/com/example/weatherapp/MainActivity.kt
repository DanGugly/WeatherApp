package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
/*
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
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
*/
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
        }

    }
    /* Below is the implementation for the app using activities
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
    }*/
