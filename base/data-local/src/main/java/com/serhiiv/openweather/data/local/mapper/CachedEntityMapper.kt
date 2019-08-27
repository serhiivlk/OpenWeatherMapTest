package com.serhiiv.openweather.data.local.mapper

import com.serhiiv.openweather.data.local.model.base.CachedEntity

interface CachedEntityMapper<Cached : CachedEntity, Entity> {

    fun mapToCashed(entity: Entity): Cached

    fun mapFromCashed(cached: Cached): Entity
}
