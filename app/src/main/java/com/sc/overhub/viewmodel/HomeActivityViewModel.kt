package com.sc.overhub.viewmodel

import android.app.Activity
import android.view.MenuItem
import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sc.overhub.R

class HomeActivityViewModel(activity: Activity) : ViewModel() {
    var navController: NavController = NavController(activity)
        set(controller) {
            field = controller
            field.addOnDestinationChangedListener(destinationChangedListener)
        }

    var visibleToolbar = ObservableInt(View.GONE)

    val bottomNavigationListener = object :
        BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.menu_statistic -> navController.navigate(R.id.statisticsFragment)
                R.id.menu_tracker -> navController.navigate(R.id.trackerFragment)
                R.id.menu_wiki -> navController.navigate(R.id.wikiFragment)
            }

            return true
        }
    }

    private val destinationChangedListener: NavController.OnDestinationChangedListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            val destId = destination.id
            if (destId != R.id.wikiFragment && destId != R.id.statisticsFragment && destId != R.id.trackerFragment) {
                visibleToolbar.set(View.VISIBLE)
            } else {
                visibleToolbar.set(View.GONE)
            }
        }

    fun onToolbarBackPressed() {
        val destId = navController.currentDestination?.id

        if (destId != R.id.wikiFragment && destId != R.id.statisticsFragment && destId != R.id.trackerFragment)
            navController.popBackStack()
    }
}