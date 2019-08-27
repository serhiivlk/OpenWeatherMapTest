package com.serhiiv.openweather.domain.interactor

import com.serhiiv.openweather.core.domain.interactor.StoreSelectedCity
import com.serhiiv.openweather.core.domain.interactor.base.BaseUseCase
import com.serhiiv.openweather.core.exception.Failure
import com.serhiiv.openweather.core.functional.Either
import com.serhiiv.openweather.domain.repository.SelectableCityRepository
import javax.inject.Inject

class StoreSelectedCityUseCaseImpl @Inject constructor(
    private val selectableCityRepository: SelectableCityRepository
) : StoreSelectedCity {
    override suspend fun execute(params: StoreSelectedCity.Params): Either<Failure, BaseUseCase.None> {
        return try {
            selectableCityRepository.storeCityName(params.cityName)
            Either.Right(BaseUseCase.None())
        } catch (e: Exception) {
            Either.Left(StoreSelectedCity.Failure(e))
        }
    }
}
