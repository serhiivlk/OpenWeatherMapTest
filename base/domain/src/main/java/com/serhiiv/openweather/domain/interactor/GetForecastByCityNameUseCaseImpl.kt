package com.serhiiv.openweather.domain.interactor

import com.serhiiv.openweather.core.domain.interactor.GetForecastByCityName
import com.serhiiv.openweather.core.exception.Failure
import com.serhiiv.openweather.core.functional.Either
import com.serhiiv.openweather.core.model.Forecast
import com.serhiiv.openweather.domain.repository.ForecastRepository
import javax.inject.Inject

class GetForecastByCityNameUseCaseImpl @Inject constructor(
    private val forecastRepository: ForecastRepository
) : GetForecastByCityName {
    override suspend fun execute(params: GetForecastByCityName.Params): Either<Failure, Forecast> {
        return try {
            val forecast = forecastRepository.getForecastByCityName(params.cityName, params.units)
            Either.Right(forecast)
        } catch (e: Exception) {
            Either.Left(GetForecastByCityName.GetForecastByCityNameFailure(e))
        }
    }
}
