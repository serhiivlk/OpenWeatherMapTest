package com.serhiiv.openweather.data.local.db.constants

object ListConstants {

    const val TABLE_NAME = "list"

    const val QUERY_LIST = "SELECT * FROM $TABLE_NAME"

    const val QUERY_DELETE_ALL = "DELETE FROM $TABLE_NAME"
}
