package com.serhiiv.openweather.weather

import com.serhiiv.openweather.core.android.base.BaseViewModel
import com.serhiiv.openweather.core.tools.Logger
import com.serhiiv.openweather.domain.exception.Failure
import com.serhiiv.openweather.domain.interactor.GetForecastByCityName
import com.serhiiv.openweather.domain.model.Forecast
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val getForecastByCityName: GetForecastByCityName
) : BaseViewModel() {

    private val mutableState = MutableLiveData<WeatherState>()

    val state: LiveData<WeatherState> get() = mutableState

    fun loadData() {
        mutableState.value = WeatherState.Loading
        val params = GetForecastByCityName.Params("London")
        getForecastByCityName(viewModelScope, params) {
            it.either(this::handleFailure, this::handleSuccess)
        }
    }

    private fun handleSuccess(forecast: Forecast) {
        mutableState.value = WeatherState.Success(forecast)
    }

    private fun handleFailure(failure: Failure) {
        when (failure) {
            is GetForecastByCityName.GetForecastByCityNameFailure -> {
                val error = failure.error
                mutableState.value = WeatherState.Error(error)
                Logger.e(error)
            }
            else -> {
                Logger.e(failure.toString())
            }
        }
    }

    sealed class WeatherState {
        object Loading : WeatherState()
        data class Success(val forecast: Forecast) : WeatherState()
        data class Error(val error: Exception) : WeatherState()
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val viewModel: WeatherViewModel
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return viewModel as T
        }
    }
}
