package com.sc.overhub.viewmodel

import android.app.Activity
import android.view.MenuItem
import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sc.overhub.R

class HomeActivityViewModel(activity: Activity) : ViewModel() {
    var navControllers: List<NavController> = mutableListOf(
        Navigation.findNavController(activity, R.id.statistic_host_fragment),
        Navigation.findNavController(activity, R.id.tracker_host_fragment),
        Navigation.findNavController(activity, R.id.wiki_host_fragment)
    )
        set(value) {
            field = value
            field.forEach { it.addOnDestinationChangedListener(destinationChangedListener) }
        }

    var currentController: NavController = navControllers[2]
        private set

    var visibleToolbar = ObservableInt(View.GONE)

    var visibleStatistic = ObservableInt(View.GONE)
    var visibleTracker = ObservableInt(View.GONE)
    var visibleWiki = ObservableInt(View.VISIBLE)

    val bottomNavigationListener = object :
        BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            handleTab(item.itemId)

            return true
        }
    }

    private val destinationChangedListener: NavController.OnDestinationChangedListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            handleToolbarHiding(destination.id)
        }

    fun onToolbarBackPressed() {
        val destId = currentController.currentDestination?.id

        if (destId != R.id.wikiFragment && destId != R.id.statisticsFragment && destId != R.id.trackerFragment)
            currentController.navigateUp()
    }

    private fun handleToolbarHiding(destId: Int?) {
        if (destId != R.id.wikiFragment &&
            destId != R.id.statisticsFragment &&
            destId != R.id.trackerFragment
        ) {
            visibleToolbar.set(View.VISIBLE)
        } else {
            visibleToolbar.set(View.GONE)
        }
    }

    private fun handleTab(itemId: Int) {
        var navControllerId: Int? = null
        when (itemId) {
            R.id.menu_statistic -> {
                visibleStatistic.set(View.VISIBLE)
                visibleTracker.set(View.GONE)
                visibleWiki.set(View.GONE)

                navControllerId = 0
            }
            R.id.menu_tracker -> {
                visibleStatistic.set(View.GONE)
                visibleStatistic.set(View.VISIBLE)
                visibleWiki.set(View.GONE)

                navControllerId = 1
            }
            R.id.menu_wiki -> {
                visibleStatistic.set(View.GONE)
                visibleTracker.set(View.GONE)
                visibleWiki.set(View.VISIBLE)

                navControllerId = 2
            }
        }

        if (navControllerId != null) {
            currentController = navControllers[navControllerId]
        }

        handleToolbarHiding(currentController.currentDestination?.id)
    }
}