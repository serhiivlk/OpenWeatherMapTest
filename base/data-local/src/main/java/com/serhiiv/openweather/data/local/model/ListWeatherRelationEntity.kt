package com.serhiiv.openweather.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.piotrek1543.android.boilerplate.cache.model.WeatherCachedEntity

@Entity(
    tableName = "list_weather_relation",
    foreignKeys = [
        ForeignKey(
            entity = ListCachedEntity::class,
            parentColumns = ["dt"],
            childColumns = ["listDt"]
        ),
        ForeignKey(
            entity = WeatherCachedEntity::class,
            parentColumns = ["id"],
            childColumns = ["weatherId"]
        )
    ]
)
data class ListWeatherRelationEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var listDt: Long? = null,
    var weatherId: Long? = null
)
