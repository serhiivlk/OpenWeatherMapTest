package com.serhiiv.openweather.core.domain.interactor

import com.serhiiv.openweather.core.domain.interactor.base.BaseUseCase
import com.serhiiv.openweather.core.exception.Failure
import com.serhiiv.openweather.core.model.SelectableCity

interface GetAllSelectableCities : BaseUseCase<List<SelectableCity>, BaseUseCase.NoParams> {
    data class GetAllSelectableCitiesFailure(val error: Exception) : Failure.FeatureFailure()
}
