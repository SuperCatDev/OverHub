package com.sc.overhub.viewmodel

import android.app.Activity
import android.os.Bundle
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
                R.id.menu_statistic -> handleTabClick(item.itemId, R.id.statisticsFragment)
                R.id.menu_tracker -> handleTabClick(item.itemId, R.id.trackerFragment)
                R.id.menu_wiki -> handleTabClick(item.itemId, R.id.wikiFragment)
                else -> return false
            }

            return true
        }
    }

    private var destinationsHistory = HashMap<Int, Bundle>()

    init {
        destinationsHistory[R.id.menu_statistic] = Bundle()
        destinationsHistory[R.id.menu_tracker] = Bundle()
        destinationsHistory[R.id.menu_wiki] = Bundle()
    }

    private var currentTabId = R.id.menu_wiki

    private val destinationChangedListener: NavController.OnDestinationChangedListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            val oldDestId = destination.id
            if (oldDestId != R.id.wikiFragment &&
                oldDestId != R.id.statisticsFragment &&
                oldDestId != R.id.trackerFragment
            ) {
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

    private fun handleTabClick(tabId: Int, hostFragmentId: Int) {
        if(currentTabId != tabId) {
            destinationsHistory[currentTabId] = navController.saveState() ?: Bundle()

            navController.restoreState(destinationsHistory[tabId] ?: Bundle())
            //todo some shit
            navController.navigateUp()
        }else {
            destinationsHistory[tabId] = Bundle()
            if (navController.currentDestination?.id != hostFragmentId) {
                navController.navigate(hostFragmentId)
            }
        }
        /*
        if(currentTabId != tabId) {
            if(navController.currentDestination?.id != hostFragmentId) {
                destinationsHistory[currentTabId] = navController.saveState() ?: Bundle()
            } else {
                destinationsHistory[currentTabId] = Bundle()
            }

            val bundle = destinationsHistory[tabId] ?: Bundle()

            if(!bundle.isEmpty) {
                navController.restoreState(bundle)
                navController.setGraph(R.navigation.main_graph)
            } else {
                navController.navigate(hostFragmentId)
            }
        } else {
            destinationsHistory[tabId] = Bundle()
            if (navController.currentDestination?.id != hostFragmentId) {
                navController.navigate(hostFragmentId)
            }
        }
*/
        currentTabId = tabId
    }
}