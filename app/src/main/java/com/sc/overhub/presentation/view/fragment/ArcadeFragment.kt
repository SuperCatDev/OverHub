package com.sc.overhub.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.sc.overhub.R
import com.sc.overhub.databinding.FragmentArcadeBinding
import com.sc.overhub.presentation.view.adapter.ArcadeAdapter
import com.sc.overhub.presentation.viewmodel.ArcadeViewModel
import com.sc.overhub.presentation.viewmodel.getViewModel
import kotlinx.android.synthetic.main.fragment_arcade.view.*

class ArcadeFragment : BaseFragment() {
    private val viewModel: ArcadeViewModel by lazy {
        getViewModel {
            ArcadeViewModel()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding: FragmentArcadeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_arcade, container, false
        )

        fragmentBinding.root.fragment_arcade_recycler.adapter = ArcadeAdapter(viewModel)
        fragmentBinding.root.fragment_arcade_recycler.layoutManager = LinearLayoutManager(activity)

        fragmentBinding.lifecycleOwner = viewLifecycleOwner
        fragmentBinding.viewModel = viewModel

        return fragmentBinding.root
    }
}