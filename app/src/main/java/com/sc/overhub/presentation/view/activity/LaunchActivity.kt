package com.sc.overhub.presentation.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.sc.overhub.R
import com.sc.overhub.databinding.LaunchScreenBinding
import com.sc.overhub.presentation.view.custom.hide
import com.sc.overhub.presentation.view.custom.show
import com.sc.overhub.presentation.viewmodel.LaunchViewModel
import kotlinx.android.synthetic.main.battle_tag_dialog_layout.view.*
import kotlinx.android.synthetic.main.launch_screen.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LaunchActivity : BaseActivity() {
    private val viewModel by viewModel<LaunchViewModel>()
    private var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<LaunchScreenBinding>(this, R.layout.launch_screen)
        binding.logoId = R.drawable.launch_logo
        binding.titleId = R.drawable.launch_title

        progressBar.show()

        viewModel.observeLaunchStatus().observe(this, Observer {
            if (it) {
                dialog?.cancel()
                startApp()
            } else {
                showBattleTagDialog()
            }
        })

        viewModel.observeErrorMessages().observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (dialog != null) dialog!!.cancel()
    }

    @SuppressLint("InflateParams")
    private fun showBattleTagDialog() {
        val hostView = layoutInflater.inflate(R.layout.battle_tag_dialog_layout, null)

        dialog = AlertDialog.Builder(this).apply {
            setView(hostView)
            setPositiveButton("Apply", null)
            setCancelable(false)

        }.show()

        dialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.setOnClickListener {
            viewModel.setBattleTag(hostView.battleTag_et.text.toString())
            dialog?.cancel()
            progressBar.show()
        }

        progressBar.hide()
    }

    private fun startApp() {
        startActivity(Intent(this@LaunchActivity, HomeActivity::class.java))
        finish()
    }
}