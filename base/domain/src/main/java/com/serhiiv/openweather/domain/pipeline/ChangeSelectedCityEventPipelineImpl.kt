package com.serhiiv.openweather.domain.pipeline

import com.serhiiv.openweather.core.domain.pipeline.ChangeSelectedCityEventPipeline
import com.serhiiv.openweather.core.domain.pipeline.base.BaseEventPipelineImpl
import javax.inject.Inject

class ChangeSelectedCityEventPipelineImpl @Inject constructor() : BaseEventPipelineImpl<String>(),
    ChangeSelectedCityEventPipeline
