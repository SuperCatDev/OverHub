package com.sc.overhub.presentation.view.activity

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sc.overhub.R
import com.sc.overhub.databinding.ActivityMainBinding
import com.sc.overhub.presentation.viewmodel.HomeActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity() {
    private val viewModel by viewModel<HomeActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        viewModel.observeVisibleArcade().observe(this, Observer {
            arcade_container.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.observeVisibleStatistic().observe(this, Observer {
            statistic_container.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.observeVisibleWiki().observe(this, Observer {
            wiki_container.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.observePopEvent().observe(this, Observer {
            getAttachedController().apply { popBackStack(graph.startDestination, false) }
        })

        bottom_navigation.selectedItemId = viewModel.currentTabId.getMenuId()
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            viewModel.handleTab(getScreenId(item.itemId))

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
        val navId = viewModel.currentTabId.getId()

        return Navigation.findNavController(this, navId)
    }

    private fun HomeActivityViewModel.ScreenId.getId(): Int = when (this) {
        HomeActivityViewModel.ScreenId.STATISTIC -> R.id.statistic_host_fragment
        HomeActivityViewModel.ScreenId.ARCADE -> R.id.arcade_host_fragment
        HomeActivityViewModel.ScreenId.WIKI -> R.id.wiki_host_fragment
    }

    private fun HomeActivityViewModel.ScreenId.getMenuId(): Int = when (this) {
        HomeActivityViewModel.ScreenId.STATISTIC -> R.id.menu_statistic
        HomeActivityViewModel.ScreenId.ARCADE -> R.id.menu_arcade
        HomeActivityViewModel.ScreenId.WIKI -> R.id.menu_wiki
    }


    private fun getScreenId(id: Int): HomeActivityViewModel.ScreenId = when (id) {
        R.id.menu_statistic -> HomeActivityViewModel.ScreenId.STATISTIC
        R.id.menu_arcade -> HomeActivityViewModel.ScreenId.ARCADE
        R.id.menu_wiki -> HomeActivityViewModel.ScreenId.WIKI
        else -> viewModel.currentTabId
    }
}
