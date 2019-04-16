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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            viewModel = vm
        }

        bottom_navigation.selectedItemId = R.id.menu_statistic
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
            R.id.menu_arcade -> {
                R.id.arcade_host_fragment
            }
            R.id.menu_wiki -> {
                R.id.wiki_host_fragment
            }
            else -> R.id.wiki_host_fragment
        }

        return Navigation.findNavController(this, navId)
    }

    private fun handleTab(itemId: Int) {
        val controller = getAttachedController()

        if (vm.oldTabId == itemId) {
            controller.popBackStack(controller.graph.startDestination, false)
            return
        }

        when (itemId) {
            R.id.menu_statistic -> {
                vm.visibleStatistic.set(View.VISIBLE)
                vm.visibleArcade.set(View.GONE)
                vm.visibleWiki.set(View.GONE)
            }
            R.id.menu_arcade -> {
                vm.visibleStatistic.set(View.GONE)
                vm.visibleArcade.set(View.VISIBLE)
                vm.visibleWiki.set(View.GONE)
            }
            R.id.menu_wiki -> {
                vm.visibleStatistic.set(View.GONE)
                vm.visibleArcade.set(View.GONE)
                vm.visibleWiki.set(View.VISIBLE)
            }
        }

        vm.oldTabId = itemId
    }
}
