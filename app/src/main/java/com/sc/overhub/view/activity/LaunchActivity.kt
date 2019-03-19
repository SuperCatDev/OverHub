package com.sc.overhub.view.activity

import android.content.Intent
import android.os.Bundle
import com.sc.overhub.R
import com.sc.overhub.repository.RepositoryFactory
import kotlinx.coroutines.launch

class LaunchActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launch_screen)

        launch {
            RepositoryFactory.getMapRepo(applicationContext)
            RepositoryFactory.getHeroRepo(applicationContext)
            startActivity(Intent(this@LaunchActivity, HomeActivity::class.java))
            finish()
        }
    }
}