package com.serhiiv.openweather.domain.interactor

import com.serhiiv.openweather.core.domain.interactor.GetAllSelectableCities
import com.serhiiv.openweather.core.domain.interactor.base.BaseUseCase
import com.serhiiv.openweather.core.exception.Failure
import com.serhiiv.openweather.core.functional.Either
import com.serhiiv.openweather.core.model.SelectableCity
import com.serhiiv.openweather.domain.repository.SelectableCityRepository
import javax.inject.Inject

class GetAllSelectableCitiesUseCaseImpl @Inject constructor(
    private val selectableCityRepository: SelectableCityRepository
) : GetAllSelectableCities {
    override suspend fun execute(params: BaseUseCase.NoParams): Either<Failure, List<SelectableCity>> {
        return try {
            val cities = selectableCityRepository.getAll()
            Either.Right(cities)
        } catch (e: Exception) {
            Either.Left(GetAllSelectableCities.GetAllSelectableCitiesFailure(e))
        }
    }
}
