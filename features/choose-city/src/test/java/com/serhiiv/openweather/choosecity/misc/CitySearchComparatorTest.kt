package com.serhiiv.openweather.choosecity.misc

import com.serhiiv.openweather.core.model.SelectableCity
import org.junit.Assert
import org.junit.Test


class CitySearchComparatorTest {
    @Test
    fun `test sort`() {
        val city1 = SelectableCity(0L, "Kiev", "UA")
        val city2 = SelectableCity(0L, "Ikaruga", "JP")
        val cities = listOf(city1, city2)

        val sorted = cities.sortedWith(CitySearchComparator("k"))

        Assert.assertEquals(
            listOf(city1, city2),
            sorted
        )
    }
}