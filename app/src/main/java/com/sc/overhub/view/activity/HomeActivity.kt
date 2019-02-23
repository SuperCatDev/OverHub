package com.sc.overhub.view.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sc.overhub.R
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)

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
