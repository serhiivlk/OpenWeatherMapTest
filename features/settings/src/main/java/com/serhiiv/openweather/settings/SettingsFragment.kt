package com.serhiiv.openweather.settings

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.preference.ListPreference
import com.serhiiv.openweather.core.domain.pipeline.ChangeUnitsEventPipeline
import com.serhiiv.openweather.settings.base.DaggerPreferenceFragmentCompat
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsFragment : DaggerPreferenceFragmentCompat() {

    @Inject
    lateinit var changeUnitsEventPipeline: ChangeUnitsEventPipeline

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        findPreference<ListPreference>("units")?.setOnPreferenceChangeListener { preference, newValue ->
            lifecycleScope.launch {
                changeUnitsEventPipeline.send("", newValue as String)
            }
            true
        }
    }
}
