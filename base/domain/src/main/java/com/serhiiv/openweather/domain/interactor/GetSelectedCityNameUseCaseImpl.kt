package com.serhiiv.openweather.domain.interactor

import com.serhiiv.openweather.core.domain.interactor.GetSelectedCityName
import com.serhiiv.openweather.core.domain.interactor.base.BaseUseCase
import com.serhiiv.openweather.core.exception.Failure
import com.serhiiv.openweather.core.functional.Either
import com.serhiiv.openweather.domain.repository.SelectableCityRepository
import javax.inject.Inject

class GetSelectedCityNameUseCaseImpl @Inject constructor(
    private val selectableCityRepository: SelectableCityRepository
) : GetSelectedCityName {
    override suspend fun execute(params: BaseUseCase.None): Either<Failure, String> {
        return try {
            val cityName = selectableCityRepository.getSelectedCityName()
            Either.Right(cityName)
        } catch (e: Exception) {
            Either.Left(GetSelectedCityName.Failure(e))
        }
    }
}
