package com.sc.overhub.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sc.overhub.R
import com.sc.overhub.databinding.FragmentStatisticBinding
import com.sc.overhub.presentation.viewmodel.StatisticViewModel
import com.sc.overhub.presentation.viewmodel.getViewModel

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

        fragmentBinding.setLifecycleOwner { lifecycle }
        fragmentBinding.viewModel = viewModel

        return fragmentBinding.root
    }
}