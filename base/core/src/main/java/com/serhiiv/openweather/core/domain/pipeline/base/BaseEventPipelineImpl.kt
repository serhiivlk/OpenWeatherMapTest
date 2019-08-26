package com.serhiiv.openweather.core.domain.pipeline.base

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

@UseExperimental(FlowPreview::class, ExperimentalCoroutinesApi::class)
open class BaseEventPipelineImpl<T> : BaseEventPipeline<T> {

    private val channel: BroadcastChannel<EventPipeline<T>> = ConflatedBroadcastChannel()

    override suspend fun send(tag: String, entity: T) {
        channel.send(EventPipeline(tag, entity))
    }

    override suspend fun asFlow(tag: String): Flow<T> {
        return channel.asFlow().filter { it.tag == tag }.map { it.data }
    }
}
