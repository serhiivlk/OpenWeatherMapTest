package com.serhiiv.openweather.data.local.db.constants

object ForecastConstants {

    const val TABLE_NAME = "forecast"

    const val QUERY_FORECAST = "SELECT * FROM $TABLE_NAME LIMIT 1"

    const val QUERY_DELETE_ALL = "DELETE FROM $TABLE_NAME"

}
