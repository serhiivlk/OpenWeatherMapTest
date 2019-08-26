package com.serhiiv.openweather.core.domain.pipeline.base

data class EventPipeline<T>(val tag: String, val data: T)
