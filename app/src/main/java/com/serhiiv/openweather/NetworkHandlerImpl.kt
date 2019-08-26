package com.serhiiv.openweather

import com.serhiiv.openweather.core.android.di.App
import com.serhiiv.openweather.core.android.extention.connectivityManager
import com.serhiiv.openweather.data.NetworkHandler
import javax.inject.Inject

class NetworkHandlerImpl @Inject constructor(private val app: App) : NetworkHandler {
    override val isConnected: Boolean
        get() = app.getApplicationContext()
            .connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting == true
}
