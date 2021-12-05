package com.example.weatherapp

import android.content.Context
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
    private lateinit var forecastAdapter:ForecastAdapter
    private val networkManager by lazy{
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        forecastAdapter = ForecastAdapter()

        val activeNetwork = networkManager.activeNetworkInfo
        activeNetwork?.let { myNetwork ->
            //Check if network available i.e. connected and not null
            if(myNetwork.isConnected){
                //Here we create background task to fetch info
                Retrofit.getNetworkApi().getForecast("atlanta")
                    //Everytime you use subscribeon you switch to a worker thread
                    .subscribeOn(Schedulers.io())
                    //Observe on lets you get the data in the main thread by using android schedulers
                    .observeOn(AndroidSchedulers.mainThread())
                    //When you subscribe this is the time you can handle the error or success or the data
                    //.subscribe(this::handleSuccess, this::handleError)
                    .subscribe(
                        //Success with CityForecast object
                        {
                                cityforecast -> handleSuccess(cityforecast)
                            //Load flowers into adapter for recycler view
                            forecastAdapter.updateForecast(cityforecast.list)
                        },
                        //Error with throwable object
                        { error -> handleError(error) }
                    )
            }else{
                //Display error in a toast
                Toast.makeText(baseContext, "Connectivity issues", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.forecastRecycler.apply {
            adapter = forecastAdapter
            layoutManager = GridLayoutManager(baseContext,1)
        }
    }
    private fun handleError(error: Throwable) {
        Log.d("NetErr", error.localizedMessage)
        Toast.makeText(baseContext, error.localizedMessage, Toast.LENGTH_LONG).show()
    }

    private fun handleSuccess(forecast: CityForecast) {
        Toast.makeText(baseContext, forecast.city.name, Toast.LENGTH_LONG).show()
    }
}
