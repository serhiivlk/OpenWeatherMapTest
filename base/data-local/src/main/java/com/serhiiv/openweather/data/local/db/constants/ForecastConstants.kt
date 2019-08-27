package com.serhiiv.openweather.data.local.db.constants

object ForecastConstants {

    const val TABLE_NAME = "forecast"

    const val QUERY_FORECAST = "SELECT * FROM $TABLE_NAME"

    const val DELETE_ALL_FORECASTS = "DELETE FROM $TABLE_NAME"

}
