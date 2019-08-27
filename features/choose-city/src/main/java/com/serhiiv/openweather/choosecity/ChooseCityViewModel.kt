package com.serhiiv.openweather.choosecity

import androidx.lifecycle.*
import com.serhiiv.openweather.choosecity.misc.CitySearchComparator
import com.serhiiv.openweather.core.android.base.BaseViewModel
import com.serhiiv.openweather.core.android.navigation.Navigator
import com.serhiiv.openweather.core.domain.interactor.GetAllSelectableCities
import com.serhiiv.openweather.core.domain.interactor.StoreSelectedCity
import com.serhiiv.openweather.core.domain.interactor.base.BaseUseCase
import com.serhiiv.openweather.core.domain.pipeline.ChangeSelectedCityEventPipeline
import com.serhiiv.openweather.core.exception.Failure
import com.serhiiv.openweather.core.model.SelectableCity
import com.serhiiv.openweather.core.tools.Logger
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@UseExperimental(ExperimentalCoroutinesApi::class, FlowPreview::class)
class ChooseCityViewModel @Inject constructor(
    private val getAllSelectableCities: GetAllSelectableCities,
    private val storeSelectedCity: StoreSelectedCity,
    private val navigator: Navigator,
    private val changeSelectedCityEventPipeline: ChangeSelectedCityEventPipeline
) : BaseViewModel() {

    private val searchQueryProducer: BroadcastChannel<String> = ConflatedBroadcastChannel("")
    private val citiesProducer: BroadcastChannel<List<SelectableCity>> =
        ConflatedBroadcastChannel()

    private val queryFlow
        get() = searchQueryProducer.asFlow()
            .debounce(300)
            .distinctUntilChanged()
    private val initCitiesFlow
        get() = citiesProducer.asFlow()
    private val citiesFlow
        get() = combine(
            queryFlow,
            initCitiesFlow
        ) { query: String, cities: List<SelectableCity> ->
            when {
                query.isEmpty() -> cities
                else -> cities.filter { it.name.contains(query, ignoreCase = true) }
            }
        }
            .distinctUntilChanged()
            .map { it.take(100) }
            .combine(queryFlow) { cities, query ->
                cities.sortedWith(CitySearchComparator(query))
            }

    private val mutableState = MutableLiveData<ChooseCityState>()

    val state: LiveData<ChooseCityState> get() = mutableState

    init {
        viewModelScope.launch {
            citiesFlow.collect {
                mutableState.value = ChooseCityState.Success(it.take(100))
            }
        }
        loadData()
    }

    private fun loadData() {
        mutableState.value = ChooseCityState.Loading
        getAllSelectableCities(viewModelScope, BaseUseCase.None()) {
            it.either(this::handleFailure, this::handleSuccess)
        }
    }

    private fun handleSuccess(cities: List<SelectableCity>) {
        viewModelScope.launch {
            citiesProducer.send(cities)
        }
    }

    private fun handleFailure(failure: Failure) {
        when (failure) {
            is GetAllSelectableCities.GetAllSelectableCitiesFailure -> {
                val error = failure.error
                mutableState.value = ChooseCityState.Error(error)
                Logger.e(error)
            }
            else -> {
                Logger.e(failure.toString())
            }
        }
    }

    fun searchQuery(query: String?) {
        viewModelScope.launch {
            searchQueryProducer.send(query ?: "")
        }
    }

    fun selectCity(city: SelectableCity) {
        submitSearchQuery(city.name)
    }

    private fun submitSearchQuery(query: String?) {
        query ?: return
        val params = StoreSelectedCity.Params(query)
        storeSelectedCity(viewModelScope, params) {
            it.either({
                Logger.e("store selected city error")
            }) {
                viewModelScope.launch {
                    changeSelectedCityEventPipeline.send("", query)
                }
                navigator.actionWeatherFromChooseCity()
            }
        }
    }

    sealed class ChooseCityState {
        object Loading : ChooseCityState()
        data class Success(val cities: List<SelectableCity>) : ChooseCityState()
        data class Error(val error: Exception) : ChooseCityState()
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(private val viewModel: ChooseCityViewModel) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return viewModel as T
        }
    }
}
