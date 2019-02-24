package com.sc.overhub.view.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sc.overhub.R
import kotlinx.android.synthetic.main.activity_main.*


class HomeActivity : BaseActivity() {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val destId = destination.id
            if (destId != R.id.wikiFragment && destId != R.id.statisticsFragment && destId != R.id.trackerFragment)
                toolbar.visibility = View.VISIBLE else toolbar.visibility = View.GONE
        }

        toolbar.visibility = View.GONE
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener {
            val destId = navController.currentDestination?.id

            if (destId != R.id.wikiFragment && destId != R.id.statisticsFragment && destId != R.id.trackerFragment)
                navController.popBackStack()
        }

        bottom_navigation.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                if (item.itemId == bottom_navigation.selectedItemId) {
                    return false
                }

                when (item.itemId) {
                    R.id.menu_statistic -> navController.navigate(R.id.statisticsFragment)
                    R.id.menu_tracker -> navController.navigate(R.id.trackerFragment)
                    R.id.menu_wiki -> navController.navigate(R.id.wikiFragment)
                }

                return true
            }
        })
    }
}
