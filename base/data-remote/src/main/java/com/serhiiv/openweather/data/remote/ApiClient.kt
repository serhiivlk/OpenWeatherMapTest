package com.serhiiv.openweather.data.remote

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {

    fun <T : Any> create(service: Class<T>, configBlock: Config.() -> Unit = {}): T {
        val config = Config().apply(configBlock)
        return createRetrofit(config = config)
            .create(service)
    }

    private fun createRetrofit(config: Config): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(createMoshi()))
            .client(createOkHttpClient(config))
            .build()
    }

    private fun createMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    private fun createOkHttpClient(config: Config): OkHttpClient {
        return OkHttpClient.Builder().apply {
            if (config.loggingEnabled) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
        }.build()
    }

    data class Config internal constructor(var loggingEnabled: Boolean = false)
}
