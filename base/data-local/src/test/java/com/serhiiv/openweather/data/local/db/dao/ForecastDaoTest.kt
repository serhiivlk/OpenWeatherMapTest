package com.serhiiv.openweather.data.local.db.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.serhiiv.openweather.data.local.db.CacheDatabase
import com.serhiiv.openweather.data.local.model.ForecastCachedEntity
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class ForecastDaoTest {

    private lateinit var db: CacheDatabase
    private lateinit var forecastDao: ForecastDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, CacheDatabase::class.java)
            .allowMainThreadQueries().build()
        forecastDao = db.forecastDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.close()
    }

    @Test
    fun `write and read forecast`(): Unit = runBlocking {
        val forecast = ForecastCachedEntity(1, 100)

        forecastDao.insert(forecast)

        val cached = forecastDao.getForecast()
        assertThat(forecast).isEqualTo(cached)
        Unit
    }
}
