package com.serhiiv.openweather.tools

import android.util.Log
import com.serhiiv.openweather.core.tools.Logger
import timber.log.Timber as TimberLib

object LoggerPrinter {
    class Timber : Logger.Printer {

        override fun log(priority: Logger.Priority, tag: String, message: String, t: Throwable?) {
            val utilPriority = when (priority) {
                Logger.Priority.DEBUG -> Log.DEBUG
                Logger.Priority.ERROR -> Log.ERROR
                Logger.Priority.INFO -> Log.INFO
                Logger.Priority.WARN -> Log.WARN
            }
            TimberLib.tag(tag).log(utilPriority, t, message)
        }
    }
}