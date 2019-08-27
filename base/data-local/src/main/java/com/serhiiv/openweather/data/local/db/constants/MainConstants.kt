package com.serhiiv.openweather.data.local.db.constants

object MainConstants {

    const val TABLE_NAME = "main"

    const val QUERY_MAIN = "SELECT * FROM $TABLE_NAME"

    const val QUERY_DELETE_ALL = "DELETE FROM $TABLE_NAME"

}
