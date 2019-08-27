package com.serhiiv.openweather.core.domain.pipeline.base

import kotlinx.coroutines.flow.Flow

interface BaseEventPipeline<T> {
    suspend fun send(tag: String, entity: T)

    fun asFlow(tag: String): Flow<T>

    class None
}
