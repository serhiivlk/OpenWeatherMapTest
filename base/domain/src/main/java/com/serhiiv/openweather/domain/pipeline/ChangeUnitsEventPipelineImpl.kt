package com.serhiiv.openweather.domain.pipeline

import com.serhiiv.openweather.core.domain.pipeline.ChangeUnitsEventPipeline
import com.serhiiv.openweather.core.domain.pipeline.base.BaseEventPipelineImpl
import javax.inject.Inject

class ChangeUnitsEventPipelineImpl @Inject constructor() : ChangeUnitsEventPipeline,
    BaseEventPipelineImpl<String>()
