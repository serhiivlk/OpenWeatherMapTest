package com.serhiiv.openweather.choosecity.misc

import com.serhiiv.openweather.core.model.SelectableCity

class CitySearchComparator(private val query: String) : Comparator<SelectableCity> {
    override fun compare(o1: SelectableCity, o2: SelectableCity): Int {
        val starts1 = o1.name.startsWith(query, ignoreCase = true)
        val starts2 = o2.name.startsWith(query, ignoreCase = true)
        return when {
            starts1.xor(starts2) -> when {
                starts1 -> -1
                else -> 1
            }
            else -> o1.name.compareTo(o2.name)
        }
    }
}
