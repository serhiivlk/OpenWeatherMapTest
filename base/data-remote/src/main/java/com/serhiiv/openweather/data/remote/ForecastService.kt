package com.serhiiv.openweather.data.remote


import com.serhiiv.openweather.data.remote.model.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {

    @GET("forecast/")
    suspend fun getForecastByCityName(
        @Query("q") cityName: String,
        @Query("appid") appId: String
    ): ForecastResponse
}