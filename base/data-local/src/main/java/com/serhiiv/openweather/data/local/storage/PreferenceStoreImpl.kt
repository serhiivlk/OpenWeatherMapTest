package com.serhiiv.openweather.data.local.storage


import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.serhiiv.openweather.core.android.di.App
import com.serhiiv.openweather.core.storage.PreferenceStore
import javax.inject.Inject

class PreferenceStoreImpl @Inject constructor(app: App) : PreferenceStore {
    private val preferencesStore = app.getApplicationContext().run {
        PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun putValue(key: String, value: Any, async: Boolean): Boolean {
        val editor = preferencesStore.edit()
        try {
            when (value) {
                is String -> editor.putString(key, value)
                is Int -> editor.putInt(key, value)
                is Float -> editor.putFloat(key, value)
                is Boolean -> editor.putBoolean(key, value)
                is Long -> editor.putLong(key, value)
            }
        } finally {
            return saveChanges(async, editor)
        }
    }

    override fun isKeyExists(key: String): Boolean = preferencesStore.contains(key)

    override fun remove(key: String, async: Boolean): Boolean {
        return saveChanges(async, preferencesStore.edit().remove(key))
    }

    override fun clear(async: Boolean): Boolean {
        return saveChanges(async, preferencesStore.edit().clear())
    }

    override fun getInt(key: String, fallback: Int): Int {
        return preferencesStore.getInt(key, fallback)
    }

    override fun getString(key: String, fallback: String): String {
        return preferencesStore.getString(key, fallback) ?: fallback
    }

    private fun saveChanges(async: Boolean, editor: SharedPreferences.Editor): Boolean {
        return if (async) {
            editor.apply()
            true
        } else {
            editor.commit()
        }
    }
}
