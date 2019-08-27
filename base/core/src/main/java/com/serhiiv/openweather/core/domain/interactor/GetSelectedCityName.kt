package com.serhiiv.openweather.core.domain.interactor

import com.serhiiv.openweather.core.domain.interactor.base.BaseUseCase
import com.serhiiv.openweather.core.exception.Failure as CoreFailure

interface GetSelectedCityName : BaseUseCase<String, BaseUseCase.None> {
    data class Failure(val error: Exception) : CoreFailure.FeatureFailure()
}
