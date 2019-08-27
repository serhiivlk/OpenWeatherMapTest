package com.serhiiv.openweather.data.local.db.constants

object CityConstants {

    const val TABLE_NAME = "city"

    const val QUERY_CITY = "SELECT * FROM $TABLE_NAME"

    const val DELETE_ALL_CITIES = "DELETE FROM $TABLE_NAME"

}
