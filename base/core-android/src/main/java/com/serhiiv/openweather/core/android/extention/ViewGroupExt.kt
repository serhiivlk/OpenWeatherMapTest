package com.serhiiv.openweather.core.android.extention

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes resId: Int, attachToRoot: Boolean = false) =
    LayoutInflater.from(context).inflate(resId, this, attachToRoot)
