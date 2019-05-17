package com.sc.overhub.view.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sc.overhub.R
import com.sc.overhub.databinding.ActivityMainBinding
import com.sc.overhub.viewmodel.HomeActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : BaseActivity() {
    private val startTabId = R.id.menu_statistic

    private val vm: HomeActivityViewModel by lazy {
        HomeActivityViewModel().apply { currentTabId = startTabId }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            viewModel = vm
        }

        bottom_navigation.selectedItemId = R.id.menu_statistic
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            handleTab(item.itemId)

            true
        }
    }

    override fun onBackPressed() {
        Log.e(TAG, "[onBackPressed]")

        val controller = getAttachedController()

        if (controller.graph.startDestination == controller.currentDestination?.id) {
            super.onBackPressed()
        } else {
            controller.popBackStack()
        }
    }

    private fun getAttachedController(): NavController {
        val navId = when (vm.currentTabId) {
            R.id.menu_statistic -> {

                Log.e(TAG, "[getAttachedController] id for statistic")
                R.id.statistic_host_fragment
            }
            R.id.menu_arcade -> {

                Log.e(TAG, "[getAttachedController] id for arcade")
                R.id.arcade_host_fragment
            }
            R.id.menu_wiki -> {

                Log.e(TAG, "[getAttachedController] id for wiki")
                R.id.wiki_host_fragment
            }
            else -> startTabId
        }

        return Navigation.findNavController(this, navId)
    }

    private fun handleTab(itemId: Int) {
        Log.e(TAG, "[handleTab]")

        val controller = getAttachedController()

        if (vm.currentTabId == itemId) {
            getAttachedController().popBackStack(controller.graph.startDestination, false)
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

        vm.currentTabId = itemId
    }

    companion object {
        const val TAG = "HomeActivity"
    }
}
