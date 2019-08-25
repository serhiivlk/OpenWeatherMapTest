package com.serhiiv.openweather.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.serhiiv.openweather.core.android.base.BaseFragment
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
}
