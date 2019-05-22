package com.sc.overhub.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.sc.overhub.R
import com.sc.overhub.databinding.FragmentStatisticBinding
import com.sc.overhub.presentation.view.activity.HomeActivity
import com.sc.overhub.presentation.viewmodel.StatisticViewModel
import com.sc.overhub.presentation.viewmodel.getViewModel
import kotlinx.android.synthetic.main.fragment_statistic.view.*

class StatisticFragment : BaseFragment() {

    private val viewModel: StatisticViewModel by lazy {
        getViewModel {
            StatisticViewModel()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding: FragmentStatisticBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_statistic, container, false
        )

        val view = fragmentBinding.root

        view.toolbar.overflowIcon = activity!!.getDrawable(R.drawable.ic_settings_black_24dp)
        view.toolbar.setOnMenuItemClickListener {
            if (it.itemId != R.id.statistic_exit) {
                return@setOnMenuItemClickListener false
            }

            AlertDialog.Builder(activity!!).apply {
                setTitle("Are you sure what you want to leave from account?")
                setPositiveButton("Yes") { dialogInterface, _ ->
                    dialogInterface.cancel()
                    leaveFromAccount()
                }
                setNegativeButton("Cancel") { dialogInterface, _ ->
                    dialogInterface.cancel()
                }
                setCancelable(false)

            }.show()

            true
        }

        fragmentBinding.setLifecycleOwner { lifecycle }
        fragmentBinding.viewModel = viewModel

        return view
    }

    private fun leaveFromAccount() {
        viewModel.leaveFromAccount {
            val hostedActivity = activity
            if (hostedActivity is HomeActivity) {
                hostedActivity.getAttachedController()
                    .navigate(R.id.action_statisticsFragment_to_launchActivity)
            }
        }
    }
}