package com.sc.overhub.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.sc.overhub.R
import com.sc.overhub.repository.ProfileRepository
import com.sc.overhub.repository.RepositoryFactory
import com.sc.overhub.view.fragment.BattleTagDialogFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class LaunchActivity : BaseActivity() {
    val repo: ProfileRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launch_screen)

        if (TextUtils.isEmpty(repo.getBattleTag())) {
            showBattleTagDialog()
            return
        }

        startApp()
    }

    @SuppressLint("InflateParams")
    private fun showBattleTagDialog()  = launch{
        delay(100)
        BattleTagDialogFragment().show(supportFragmentManager, "BattleTagDialogFragment")

/*
        dialog = AlertDialog.Builder(this).apply {
            setView(R.layout.battle_tag_dialog_layout)
            setPositiveButton("Apply") { dialog, which ->
                if (repo.setBattleTag(battleTag.text.toString())) {
                    dialog.cancel()
                    startApp()
                    Log.e("LaunchActivity", "IN")
                }
                this.show()
                Log.e("LaunchActivity", "Out")
            }
            setCancelable(false)
        }.show()*/
    }

    fun startApp() {
        launch {
            RepositoryFactory.getMapRepo(applicationContext)
            RepositoryFactory.getHeroRepo(applicationContext)
            RepositoryFactory.getArcadeRepo(applicationContext)
            startActivity(Intent(this@LaunchActivity, HomeActivity::class.java))
            finish()
        }
    }
}