package com.serhiiv.openweather.data.local.db.constants

object WeatherConstants {

    const val TABLE_NAME = "weather"

    const val QUERY_WEATHER = "SELECT * FROM $TABLE_NAME"

    const val DELETE_ALL_WEATHERS = "DELETE FROM $TABLE_NAME"

}
