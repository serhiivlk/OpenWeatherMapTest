package com.serhiiv.openweather

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.serhiiv.openweather.core.android.base.BaseActivity
import com.serhiiv.openweather.core.android.extention.setupToolbar
import com.serhiiv.openweather.core.android.navigation.HasNavControllerProvider
import com.serhiiv.openweather.core.android.navigation.Navigator
import com.serhiiv.openweather.core.extension.lazyUnsychronized
import com.serhiiv.openweather.test.R
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), HasNavControllerProvider {

    @Inject
    lateinit var navigator: Navigator

    private val destinationChangeListener =
        NavController.OnDestinationChangedListener { controller, destination, arguments ->
            invalidateOptionsMenu()
        }

    override val navController: NavController by lazyUnsychronized { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar(toolbar)
        toolbar.setupWithNavController(navController)
    }

    override fun onStart() {
        super.onStart()
        navController.addOnDestinationChangedListener(destinationChangeListener)
    }

    override fun onStop() {
        super.onStop()
        navController.removeOnDestinationChangedListener(destinationChangeListener)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        when (navController.currentDestination?.id) {
            R.id.weatherFragment -> {
                menuInflater.inflate(R.menu.weather, menu)
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_item_settings -> {
                navigator.actionSettingsFromWeather()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
