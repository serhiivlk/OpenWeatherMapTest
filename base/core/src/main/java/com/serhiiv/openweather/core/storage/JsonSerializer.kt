package com.serhiiv.openweather.core.storage

interface JsonSerializer {

    fun serialize(data: Any): String

    fun <T> deserialize(data: String, type: Class<T>): T

    fun <T> deserializeList(data: String, type: Class<Array<T>>): List<T>
}
