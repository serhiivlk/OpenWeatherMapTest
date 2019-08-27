package com.serhiiv.openweather.data.local.datasource

import com.serhiiv.openweather.core.android.di.App
import com.serhiiv.openweather.core.extension.lazyUnsychronized
import com.serhiiv.openweather.core.storage.PreferenceStore
import com.serhiiv.openweather.data.datasource.SelectableCityLocalDataSource
import com.serhiiv.openweather.data.local.mapper.SelectableCityEntityMapper
import com.serhiiv.openweather.data.local.model.SelectableCityJsonEntity
import com.serhiiv.openweather.data.model.SelectableCityEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.Reader
import javax.inject.Inject

class SelectableCityLocalDataSourceImpl @Inject constructor(
    private val app: App,
    private val selectableCityEntityMapper: SelectableCityEntityMapper,
    private val preferenceStore: PreferenceStore
) : SelectableCityLocalDataSource {
    private val moshi by lazyUnsychronized { Moshi.Builder().build() }
    private val selectableCityMoshiAdapter by lazyUnsychronized {
        val type =
            Types.newParameterizedType(List::class.java, SelectableCityJsonEntity::class.java)
        moshi.adapter<List<SelectableCityJsonEntity>>(type)
    }

    override suspend fun getAll(): List<SelectableCityEntity> {
        return selectableCityMoshiAdapter.fromJson(readJsonFromAsset()).orEmpty()
            .map(selectableCityEntityMapper::mapFromCashed)
    }

    override suspend fun getSelectedCityName(): String {
        return preferenceStore.getString(SELECTED_CITY_PREF_KEY, "")
    }

    override suspend fun storeCityName(cityName: String) {
        preferenceStore.putValue(SELECTED_CITY_PREF_KEY, cityName, false)
    }

    private fun readJsonFromAsset(): String = try {
        val assets = app.getApplicationContext().assets
        val inputStream = assets.open("city.list.min.json")
        inputStream.bufferedReader().let(Reader::readText)
    } catch (e: Exception) {
        "[]"
    }

    companion object {
        private const val SELECTED_CITY_PREF_KEY = "selected_city_name"
    }
}
