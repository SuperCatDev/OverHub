package com.sc.overhub.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sc.overhub.R
import com.sc.overhub.databinding.FragmentArcadeBinding
import com.sc.overhub.viewmodel.ArcadeViewModel
import com.sc.overhub.viewmodel.getViewModel

class ArcadeFragment : BaseFragment() {

    private val viewModel: ArcadeViewModel by lazy {
        getViewModel{
            ArcadeViewModel()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding: FragmentArcadeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_arcade, container, false
        )

        fragmentBinding.viewModel = viewModel

        return fragmentBinding.root
    }
}