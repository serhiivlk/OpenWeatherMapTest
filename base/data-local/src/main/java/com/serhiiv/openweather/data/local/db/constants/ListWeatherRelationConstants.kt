package com.serhiiv.openweather.data.local.db.constants

object ListWeatherRelationConstants {
    const val TABLE_NAME = "list_weather_relation"

    const val QUERY_CITY = "SELECT * FROM $TABLE_NAME"

    const val QUERY_DELETE_ALL = "DELETE FROM $TABLE_NAME"
}
