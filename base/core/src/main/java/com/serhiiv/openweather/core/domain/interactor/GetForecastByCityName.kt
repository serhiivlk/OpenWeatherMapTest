package com.serhiiv.openweather.core.domain.interactor

import com.serhiiv.openweather.core.domain.interactor.base.BaseUseCase
import com.serhiiv.openweather.core.exception.Failure
import com.serhiiv.openweather.core.model.Forecast

interface GetForecastByCityName : BaseUseCase<Forecast, GetForecastByCityName.Params> {
    data class Params(val cityName: String, val units: String)

    data class GetForecastByCityNameFailure(val error: Exception) : Failure.FeatureFailure()
}
