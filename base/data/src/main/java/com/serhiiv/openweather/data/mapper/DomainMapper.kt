package com.serhiiv.openweather.data.mapper

interface DomainMapper<out To, in From> {
    fun map(from: From): To

    operator fun invoke(from: From): To = map(from)

    operator fun invoke(list: List<From>): List<To> = list.map(this::map)
}
