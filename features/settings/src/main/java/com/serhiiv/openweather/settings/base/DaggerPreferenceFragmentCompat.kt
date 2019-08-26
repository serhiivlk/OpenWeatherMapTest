package com.serhiiv.openweather.settings.base

import android.content.Context
import androidx.preference.PreferenceFragmentCompat
import dagger.android.support.AndroidSupportInjection

abstract class DaggerPreferenceFragmentCompat : PreferenceFragmentCompat() {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }
}
