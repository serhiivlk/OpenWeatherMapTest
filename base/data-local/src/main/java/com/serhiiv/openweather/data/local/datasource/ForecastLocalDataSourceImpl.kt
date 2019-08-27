package com.serhiiv.openweather.data.local.datasource

import com.piotrek1543.android.boilerplate.cache.model.MainCachedEntity
import com.piotrek1543.android.boilerplate.cache.model.WeatherCachedEntity
import com.serhiiv.openweather.data.datasource.ForecastLocalDataSource
import com.serhiiv.openweather.data.local.db.dao.*
import com.serhiiv.openweather.data.local.mapper.*
import com.serhiiv.openweather.data.local.model.ListCachedEntity
import com.serhiiv.openweather.data.model.ForecastEntity
import javax.inject.Inject

class ForecastLocalDataSourceImpl @Inject constructor(
    private val forecastDao: ForecastDao,
    private val listDao: ListDao,
    private val cityDao: CityDao,
    private val mainDao: MainDao,
    private val weatherDao: WeatherDao,
    private val forecastMapper: ForecastEntityMapper,
    private val cityMapper: CityEntityMapper,
    private val listMapper: ListEntityMapper,
    private val mainMapper: MainEntityMapper,
    private val weatherMapper: WeatherEntityMapper
) : ForecastLocalDataSource {
    override suspend fun clearForecast() {
        forecastDao.deleteAll()
        cityDao.deleteAll()
        listDao.deleteAll()
        mainDao.deleteAll()
        weatherDao.deleteAll()
    }

    override suspend fun storeForecast(entity: ForecastEntity) {
        val forecast = forecastMapper.mapToCashed(entity)
        val city = entity.city?.let(cityMapper::mapToCashed)

        val lists = mutableListOf<ListCachedEntity>()
        val mains = mutableListOf<MainCachedEntity>()
        val weathers = mutableSetOf<WeatherCachedEntity>()
        for (item in entity.list) {
            val weather = item.weather ?: continue
            val main = item.main ?: continue
            lists.add(listMapper.mapToCashed(item))
            mains.add(mainMapper.mapToCashed(main).copy(dtKey = item.dt))
            weathers.add(weatherMapper.mapToCashed(weather).copy(dtKey = item.dt))
        }

        clearForecast()

        forecastDao.insert(forecast)
        city?.let { cityDao.insert(it) }
        listDao.insert(lists)
        mainDao.insert(mains)
        weatherDao.insert(weathers.toList())
    }

    override suspend fun getForecast(): ForecastEntity {
        val forecast = forecastDao.getForecast()
        val city = cityDao.getCity()?.let(cityMapper::mapFromCashed)

        val mains = mainDao.getAll().toMutableList()
        val weathers = weatherDao.getAll().toMutableList()
        val list = listDao.getAll().map { item ->
            val main = mains.find { it.dtKey == item.dt }.also { mains.remove(it) }
            val weather = weathers.find { it.dtKey == item.dt }.also { weathers.remove(it) }
            listMapper.mapFromCashed(item)
                .copy(
                    main = main?.let(mainMapper::mapFromCashed),
                    weather = weather?.let(weatherMapper::mapFromCashed)
                )
        }

        return forecast.let(forecastMapper::mapFromCashed)
            .copy(
                city = city,
                list = list
            )
    }
}
