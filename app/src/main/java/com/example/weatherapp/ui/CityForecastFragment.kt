package com.example.weatherapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.adapter.ForecastAdapter
import com.example.weatherapp.adapter.ForecastDetailsClick
import com.example.weatherapp.databinding.FragmentCityForecastBinding
import com.example.weatherapp.model.CityForecast
import com.example.weatherapp.model.Forecast
import com.example.weatherapp.rest.Retrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class CityForecastFragment : Fragment(),ForecastDetailsClick {
    // this variable is for view binding
    private lateinit var binding: FragmentCityForecastBinding
    // variable to get the city
    private var cityData: String? = null

    private lateinit var forecastAdapter: ForecastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        forecastAdapter = ForecastAdapter(this,cityData)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCityForecastBinding.inflate(inflater, container, false)
        cityData = arguments?.getString(CITY_NAME)

        // here I am setting my recycler view
        binding.forecastRecycler.apply {
            // adding the layout manager
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            // setting the adapter
            adapter = forecastAdapter
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        cityData?.let {
            Retrofit.getNetworkApi().getForecast(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { forecast -> handleSuccess(forecast) },
                    { throwable -> handleError(throwable) }
                )
        } ?: Toast.makeText(requireContext(), "Please enter a valid city", Toast.LENGTH_LONG).show()
    }
    private fun handleError(error: Throwable) {
        Log.d("NetErr", error.localizedMessage)
        Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_LONG).show()
    }

    private fun handleSuccess(forecast: CityForecast) {
        Toast.makeText(requireContext(), forecast.city.name, Toast.LENGTH_LONG).show()
        forecastAdapter.updateForecast(forecast)
    }

    companion object {
        const val FORECAST_BUNDLE = "FORECAST_BUNDLE"

        @JvmStatic
        fun newInstance() =
            CityForecastFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
    override fun moveToForecastDetails(cityName: String, forecast: Forecast) {
        findNavController().navigate(
            R.id.ForecastDetailsFragment,
            bundleOf(
                //FORECAST_BUNDLE to forecast
            "DATE" to forecast.dtTxt,
            "WIND_SPEED" to forecast.wind.speed.toString(),
                "HUMIDITY" to forecast.main.humidity.toString(),
                "PRESSURE" to forecast.main.humidity.toString(),
                "DESCRIPTION" to forecast.weather[0].description
            )
        )
    }
}