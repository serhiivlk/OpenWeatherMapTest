package com.serhiiv.openweather.domain.interactor

import com.serhiiv.openweather.domain.exception.Failure
import com.serhiiv.openweather.domain.functional.Either
import com.serhiiv.openweather.domain.model.Forecast
import com.serhiiv.openweather.domain.repository.ForecastRepository
import javax.inject.Inject

class GetForecastByCityName @Inject constructor(
    private val forecastRepository: ForecastRepository
) : UseCase<Forecast, GetForecastByCityName.Params>() {
    override suspend fun execute(params: Params): Either<Failure, Forecast> {
        return try {
            val forecast = forecastRepository.getForecastByCityName(params.cityName, params.units)
            Either.Right(forecast)
        } catch (e: Exception) {
            Either.Left(GetForecastByCityNameFailure(e))
        }
    }

    data class Params(internal val cityName: String, internal val units: String)

    data class GetForecastByCityNameFailure(val error: Exception) : Failure.FeatureFailure()
}