package com.sc.overhub.presentation.view.activity

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sc.overhub.R
import com.sc.overhub.databinding.ActivityMainBinding
import com.sc.overhub.presentation.viewmodel.HomeActivityViewModel
import com.sc.overhub.presentation.viewmodel.getViewModel
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : BaseActivity() {
    private val startTabId = R.id.menu_statistic

    private val vm: HomeActivityViewModel by lazy {
        getViewModel {
            HomeActivityViewModel().apply { currentTabId = startTabId }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            lifecycleOwner = this@HomeActivity
            viewModel = vm
        }

        bottom_navigation.selectedItemId = vm.currentTabId
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            handleTab(item.itemId)

            true
        }
    }

    override fun onBackPressed() {
        val controller = getAttachedController()

        if (controller.graph.startDestination == controller.currentDestination?.id) {
            super.onBackPressed()
        } else {
            controller.popBackStack()
        }
    }

    fun getAttachedController(): NavController {
        val navId = when (vm.currentTabId) {
            R.id.menu_statistic -> {
                R.id.statistic_host_fragment
            }
            R.id.menu_arcade -> {
                R.id.arcade_host_fragment
            }
            R.id.menu_wiki -> {
                R.id.wiki_host_fragment
            }
            else -> startTabId
        }

        return Navigation.findNavController(this, navId)
    }

    private fun handleTab(itemId: Int) {
        val controller = getAttachedController()

        if (vm.currentTabId == itemId) {
            getAttachedController().popBackStack(controller.graph.startDestination, false)
            return
        }

        when (itemId) {
            R.id.menu_statistic -> {
                vm.visibleStatistic.value = View.VISIBLE
                vm.visibleArcade.value = View.GONE
                vm.visibleWiki.value = View.GONE
            }
            R.id.menu_arcade -> {
                vm.visibleStatistic.value = View.GONE
                vm.visibleArcade.value = View.VISIBLE
                vm.visibleWiki.value = View.GONE
            }
            R.id.menu_wiki -> {
                vm.visibleStatistic.value = View.GONE
                vm.visibleArcade.value = View.GONE
                vm.visibleWiki.value = View.VISIBLE
            }
        }

        vm.currentTabId = itemId
    }
}
