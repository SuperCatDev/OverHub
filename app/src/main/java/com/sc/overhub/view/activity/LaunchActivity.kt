package com.sc.overhub.view.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.sc.overhub.R
import com.sc.overhub.repository.ProfileRepository
import com.sc.overhub.repository.RepositoryFactory
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class LaunchActivity : BaseActivity() {
    private val repo: ProfileRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launch_screen)

        if (TextUtils.isEmpty(repo.getBattleTag())) {
            Toast.makeText(this, "BattleTag is empty", Toast.LENGTH_LONG).show()
            return
        }

        launch {
            RepositoryFactory.getMapRepo(applicationContext)
            RepositoryFactory.getHeroRepo(applicationContext)
            RepositoryFactory.getArcadeRepo(applicationContext)
            startActivity(Intent(this@LaunchActivity, HomeActivity::class.java))
            finish()
        }
    }
}