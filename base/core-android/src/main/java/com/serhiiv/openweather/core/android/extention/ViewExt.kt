package com.serhiiv.openweather.core.android.extention

import android.view.View

infix fun View.isGone(gone: Boolean) {
    visibility = if (gone) View.GONE else View.VISIBLE
}
