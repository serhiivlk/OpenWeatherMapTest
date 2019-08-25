package com.serhiiv.openweather.test

import android.os.Bundle
import com.serhiiv.openweather.core.android.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
