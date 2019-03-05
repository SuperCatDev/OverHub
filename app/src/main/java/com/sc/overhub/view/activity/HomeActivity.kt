package com.sc.overhub.view.activity

import android.app.Activity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.sc.overhub.R
import com.sc.overhub.databinding.ActivityMainBinding
import com.sc.overhub.viewmodel.HomeActivityViewModel
import com.sc.overhub.viewmodel.getViewModel
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : BaseActivity() {
    private val vm: HomeActivityViewModel by lazy {
        getViewModel(getViewModelGetter(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            viewModel = vm
        }

        vm.navControllers = listOf(
            Navigation.findNavController(this, R.id.statistic_host_fragment),
            Navigation.findNavController(this, R.id.tracker_host_fragment),
            Navigation.findNavController(this, R.id.wiki_host_fragment)
        )

        toolbar.setNavigationOnClickListener { vm.onToolbarBackPressed() }
        bottom_navigation.setOnNavigationItemSelectedListener(vm.bottomNavigationListener)
    }

    companion object {
        fun getViewModelGetter(activity: Activity) = {
            HomeActivityViewModel(activity).also {
                it.navControllers = listOf(
                    Navigation.findNavController(activity, R.id.statistic_host_fragment),
                    Navigation.findNavController(activity, R.id.tracker_host_fragment),
                    Navigation.findNavController(activity, R.id.wiki_host_fragment)
                )
            }
        }
    }
}
