package com.serhiiv.openweather.core.domain.interactor

import com.serhiiv.openweather.core.domain.interactor.base.BaseUseCase
import com.serhiiv.openweather.core.exception.Failure as CoreFailure

interface StoreSelectedCity : BaseUseCase<BaseUseCase.None, StoreSelectedCity.Params> {
    data class Params(val cityName: String)

    data class Failure(val error: Exception) : CoreFailure.FeatureFailure()
}
