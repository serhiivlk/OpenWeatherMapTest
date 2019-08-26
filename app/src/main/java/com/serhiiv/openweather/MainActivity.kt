package com.serhiiv.openweather

import android.os.Bundle
import com.serhiiv.openweather.core.android.base.BaseActivity
import com.serhiiv.openweather.test.R

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
