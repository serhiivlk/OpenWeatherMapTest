package com.serhiiv.openweather.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import coil.api.load
import com.serhiiv.openweather.core.android.base.BaseFragment
import com.serhiiv.openweather.core.android.extention.isGone
import com.serhiiv.openweather.core.android.navigation.Navigator
import kotlinx.android.synthetic.main.fragment_weather.*
import javax.inject.Inject

class WeatherFragment : BaseFragment() {

    @Inject
    lateinit var factory: WeatherViewModel.Factory
    @Inject
    lateinit var navigator: Navigator

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

        city_name.setOnClickListener {
            navigator.actionChooseCityFromWeather()
        }

        viewModel.state.observe(viewLifecycleOwner, Observer {
            when (it) {
                is WeatherViewModel.WeatherState.Loading -> showLoading()
                is WeatherViewModel.WeatherState.Success -> showWeather(it.uiState)
                is WeatherViewModel.WeatherState.Error -> showError(it.error)
            }
        })
    }

    private fun showLoading() {
        progress_bar.show()
        weather_content isGone true
    }

    private fun showWeather(state: WeatherUiState) = with(state) {
        progress_bar.hide()
        weather_content isGone false
        city_name.text = cityName
        weather_description.text = description
        this@WeatherFragment.temp.text = temp
        temp_min_max.text = tempMinMax
        this@WeatherFragment.pressure.text = pressure
        this@WeatherFragment.humidity.text = humidity
        weather_icon.load(iconUrl)
    }

    private fun showError(e: Exception) {
        progress_bar.hide()
        Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
    }
}
