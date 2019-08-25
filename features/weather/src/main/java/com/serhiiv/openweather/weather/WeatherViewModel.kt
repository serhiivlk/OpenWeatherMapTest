package com.serhiiv.openweather.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.serhiiv.openweather.core.android.base.BaseViewModel
import javax.inject.Inject

class WeatherViewModel @Inject constructor() : BaseViewModel() {

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val viewModel: WeatherViewModel
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return viewModel as T
        }
    }
}
