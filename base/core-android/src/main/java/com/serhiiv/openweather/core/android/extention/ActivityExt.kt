package com.serhiiv.openweather.core.android.extention

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

inline fun AppCompatActivity.setupToolbar(toolbar: Toolbar, appBarBlock: (ActionBar) -> Unit = {}) {
    setSupportActionBar(toolbar)
    supportActionBar?.also(appBarBlock)
}
