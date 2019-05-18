package com.sc.overhub.presentation.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.sc.overhub.R
import com.sc.overhub.data.repository.DbRepositoryFactory
import com.sc.overhub.data.repository.ProfileRepository
import com.sc.overhub.databinding.LaunchScreenBinding
import kotlinx.android.synthetic.main.battle_tag_dialog_layout.view.*
import kotlinx.android.synthetic.main.launch_screen.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class LaunchActivity : BaseActivity() {
    private val repo: ProfileRepository by inject()
    private var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<LaunchScreenBinding>(this, R.layout.launch_screen)
        binding.logoId = R.drawable.launch_logo
        binding.titleId = R.drawable.launch_title
        checkStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (dialog != null) dialog!!.cancel()
    }

    private fun checkStart() = launch {
        if (TextUtils.isEmpty(repo.getBattleTag()))
            showBattleTagDialog()
        else startApp()
    }

    @SuppressLint("InflateParams")
    private fun showBattleTagDialog() {
        val hostView = layoutInflater.inflate(R.layout.battle_tag_dialog_layout, null)

        dialog = AlertDialog.Builder(this).apply {
            setView(hostView)
            setPositiveButton("Apply", null)
            setCancelable(false)

        }.show()

        dialog!!.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            if (repo.setBattleTag(hostView.battleTag_et.text.toString())) {
                dialog!!.cancel()
                launch { startApp() }
            } else {
                Toast.makeText(this, "You Battle Tag is not valid", Toast.LENGTH_SHORT).show()
            }
        }

        progressBar.visibility = View.GONE
    }

    private suspend fun startApp() {
        progressBar.visibility = View.VISIBLE

        DbRepositoryFactory.initAllRepo(applicationContext)
        startActivity(Intent(this@LaunchActivity, HomeActivity::class.java))
        finish()
    }
}