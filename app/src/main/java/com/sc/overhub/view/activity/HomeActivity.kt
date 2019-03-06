package com.sc.overhub.view.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sc.overhub.R
import com.sc.overhub.databinding.ActivityMainBinding
import com.sc.overhub.viewmodel.HomeActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : BaseActivity() {
    private val vm: HomeActivityViewModel by lazy {
        HomeActivityViewModel(R.id.menu_wiki)
    }

    private val destinationChangedListener: NavController.OnDestinationChangedListener =
        NavController.OnDestinationChangedListener { controller, destination, _ ->
            handleToolbarHiding(controller, destination.id)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            viewModel = vm
        }

        Navigation.findNavController(this, R.id.statistic_host_fragment)
            .addOnDestinationChangedListener(destinationChangedListener)
        Navigation.findNavController(this, R.id.tracker_host_fragment)
            .addOnDestinationChangedListener(destinationChangedListener)
        Navigation.findNavController(this, R.id.wiki_host_fragment)
            .addOnDestinationChangedListener(destinationChangedListener)

        toolbar.setNavigationOnClickListener {
            val navId = when (vm.oldTabId) {
                R.id.menu_statistic -> {
                    R.id.statistic_host_fragment
                }
                R.id.menu_tracker -> {
                    R.id.tracker_host_fragment
                }
                R.id.menu_wiki -> {
                    R.id.wiki_host_fragment
                }
                else -> R.id.wiki_host_fragment
            }

            val controller = Navigation.findNavController(this, navId)
            val destId = controller.currentDestination?.id

            if (destId != R.id.wikiFragment && destId != R.id.statisticsFragment && destId != R.id.trackerFragment)
                controller.navigateUp()
        }

        bottom_navigation.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                handleTab(item.itemId)

                return true
            }
        })
    }

    override fun onBackPressed() {
        val controller = getAttachedController()

        if (controller.graph.startDestination == controller.currentDestination?.id) {
            super.onBackPressed()
        } else {
            controller.popBackStack()
        }
    }

    private fun getAttachedController(): NavController {
        val navId = when (vm.oldTabId) {
            R.id.menu_statistic -> {
                R.id.statistic_host_fragment
            }
            R.id.menu_tracker -> {
                R.id.tracker_host_fragment
            }
            R.id.menu_wiki -> {
                R.id.wiki_host_fragment
            }
            else -> R.id.wiki_host_fragment
        }

        return Navigation.findNavController(this, navId)
    }


    private fun handleTab(itemId: Int) {
        when (itemId) {
            R.id.menu_statistic -> {
                vm.visibleStatistic.set(View.VISIBLE)
                vm.visibleTracker.set(View.GONE)
                vm.visibleWiki.set(View.GONE)
            }
            R.id.menu_tracker -> {
                vm.visibleStatistic.set(View.GONE)
                vm.visibleStatistic.set(View.VISIBLE)
                vm.visibleWiki.set(View.GONE)
            }
            R.id.menu_wiki -> {
                vm.visibleStatistic.set(View.GONE)
                vm.visibleTracker.set(View.GONE)
                vm.visibleWiki.set(View.VISIBLE)
            }
        }

        vm.oldTabId = itemId
        handleToolbarHiding(getAttachedController(), getAttachedController().currentDestination?.id)
    }

    private fun handleToolbarHiding(controller: NavController, destId: Int?) {
        if (destId != controller.graph.startDestination
        ) {
            vm.visibleToolbar.set(View.VISIBLE)
        } else {
            vm.visibleToolbar.set(View.GONE)
        }
    }

}
