package com.example.weatherapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.databinding.FragmentForecastDetailsBinding
import com.example.weatherapp.model.Forecast
import com.example.weatherapp.ui.CityForecastFragment.Companion.FORECAST_BUNDLE

class ForecastDetails : Fragment() {
    private lateinit var binding: FragmentForecastDetailsBinding
    private lateinit var date:String
    private lateinit var wind_speed:String
    private lateinit var humidity:String
    private lateinit var pressure:String
    private lateinit var description:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        date = arguments?.getString("DATE").toString()
        humidity = arguments?.getString("HUMIDITY").toString()
        wind_speed = arguments?.getString("WIND_SPEED").toString()
        pressure = arguments?.getString("PRESSURE").toString()
        description = arguments?.getString("DESCRIPTION").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForecastDetailsBinding.inflate(inflater, container, false)
        binding.date.text = date
        binding.humidity.text = humidity
        binding.windSpeed.text = wind_speed
        binding.pressure.text = pressure
        binding.description.text = description
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ForecastDetails().apply {
                arguments = Bundle().apply {

                }
            }
    }
}