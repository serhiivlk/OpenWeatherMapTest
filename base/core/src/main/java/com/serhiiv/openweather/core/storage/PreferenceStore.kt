package com.serhiiv.openweather.core.storage

interface PreferenceStore {

    fun putValue(key: String, value: Any, async: Boolean = true): Boolean

    fun isKeyExists(key: String): Boolean

    fun remove(key: String, async: Boolean = true): Boolean

    fun clear(async: Boolean = true): Boolean

    fun getInt(key: String, fallback: Int = 0): Int

    fun getString(key: String, fallback: String): String
}
