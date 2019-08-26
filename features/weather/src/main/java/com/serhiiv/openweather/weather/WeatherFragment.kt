package com.serhiiv.openweather.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.serhiiv.openweather.core.android.base.BaseFragment
import com.serhiiv.openweather.domain.model.Forecast
import javax.inject.Inject

class WeatherFragment : BaseFragment() {

    @Inject
    lateinit var factory: WeatherViewModel.Factory

    private val viewModel: WeatherViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.loadData()

        viewModel.state.observe(viewLifecycleOwner, Observer {
            when (it) {
                is WeatherViewModel.WeatherState.Loading -> showLoading()
                is WeatherViewModel.WeatherState.Success -> showForecast(it.forecast)
                is WeatherViewModel.WeatherState.Error -> showError(it.error)
            }
        })
    }

    private fun showLoading() {
        progress_bar isGone false
    }

    private fun showForecast(forecast: Forecast) {
        progress_bar isGone true
        text.text = forecast.city?.name
    }

    private fun showError(e: Exception) {
        progress_bar isGone true
        Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
    }
}
