package com.serhiiv.openweather.weather

import androidx.lifecycle.*
import com.serhiiv.openweather.core.android.base.BaseViewModel
import com.serhiiv.openweather.core.domain.pipeline.ChangeUnitsEventPipeline
import com.serhiiv.openweather.core.storage.PreferenceStore
import com.serhiiv.openweather.core.tools.Logger
import com.serhiiv.openweather.domain.exception.Failure
import com.serhiiv.openweather.domain.interactor.GetForecastByCityName
import com.serhiiv.openweather.domain.model.Forecast
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@UseExperimental(ExperimentalCoroutinesApi::class, FlowPreview::class)
class WeatherViewModel @Inject constructor(
    private val getForecastByCityName: GetForecastByCityName,
    private val changeUnitsEventPipeline: ChangeUnitsEventPipeline,
    private val preferenceStore: PreferenceStore,
    private val uiStateMapper: WeatherUiState.Mapper
) : BaseViewModel() {

    private val mutableState = MutableLiveData<WeatherState>()

    val state: LiveData<WeatherState> get() = mutableState

    init {
        viewModelScope.launch {
            changeUnitsEventPipeline.asFlow("").onStart {
                val units = preferenceStore.getString("units", "metric")
                emit(units)
            }
                .distinctUntilChanged { old, new -> old == new }
                .collect {
                    loadData(it)
                }
        }
    }

    private fun loadData(units: String) {
        mutableState.value = WeatherState.Loading
        val params = GetForecastByCityName.Params(cityName = "London", units = units)
        getForecastByCityName(viewModelScope, params) {
            it.either(this::handleFailure) { forecast -> handleSuccess(forecast, units) }
        }
    }

    private fun handleSuccess(forecast: Forecast, units: String) {
        mutableState.value = WeatherState.Success(uiStateMapper(forecast, units))
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
        data class Success(val uiState: WeatherUiState) : WeatherState()
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
