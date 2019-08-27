package com.serhiiv.openweather.data.local.mapper

import com.serhiiv.openweather.data.local.model.ListCachedEntity
import com.serhiiv.openweather.data.model.ListEntity
import javax.inject.Inject

open class ListEntityMapper @Inject constructor() :
    CachedEntityMapper<ListCachedEntity, ListEntity> {

    override fun mapToCashed(entity: ListEntity): ListCachedEntity = with(entity) {
        ListCachedEntity(
            dt = dt,
            dtTxt = dtTxt
        )
    }

    override fun mapFromCashed(cached: ListCachedEntity): ListEntity = with(cached) {
        ListEntity(
            dt = dt,
            dtTxt = dtTxt
        )
    }
}
