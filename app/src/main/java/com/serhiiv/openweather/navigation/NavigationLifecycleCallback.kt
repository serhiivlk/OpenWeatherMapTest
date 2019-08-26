package com.serhiiv.openweather.navigation

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.serhiiv.openweather.core.android.navigation.HasNavControllerProvider
import com.serhiiv.openweather.core.android.navigation.Navigator
import javax.inject.Inject

class NavigationLifecycleCallback @Inject constructor(
    private val navigator: Navigator
) : Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(activity: Activity?) {
        navigator.unbind()
    }

    override fun onActivityResumed(activity: Activity?) {
        (activity as? HasNavControllerProvider)?.also {
            navigator.bind(it.navController)
        }
    }

    override fun onActivityStarted(activity: Activity?) {}

    override fun onActivityDestroyed(activity: Activity?) {}

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {}

    override fun onActivityStopped(activity: Activity?) {}

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {}
}