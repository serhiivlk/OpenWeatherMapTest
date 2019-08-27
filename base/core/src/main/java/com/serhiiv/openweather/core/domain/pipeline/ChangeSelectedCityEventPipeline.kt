package com.serhiiv.openweather.core.domain.pipeline

import com.serhiiv.openweather.core.domain.pipeline.base.BaseEventPipeline

interface ChangeSelectedCityEventPipeline : BaseEventPipeline<String>
