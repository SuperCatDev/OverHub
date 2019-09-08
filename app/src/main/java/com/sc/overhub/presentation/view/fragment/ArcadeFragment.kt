package com.sc.overhub.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sc.overhub.R
import com.sc.overhub.databinding.FragmentArcadeBinding
import com.sc.overhub.presentation.view.adapter.ArcadeAdapter
import com.sc.overhub.presentation.viewmodel.ArcadeViewModel
import kotlinx.android.synthetic.main.fragment_arcade.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArcadeFragment : BaseFragment() {
    private val viewModel by viewModel<ArcadeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding: FragmentArcadeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_arcade, container, false
        )

        val adapter = ArcadeAdapter()

        fragmentBinding.root.fragment_arcade_recycler.adapter = adapter
        fragmentBinding.root.fragment_arcade_recycler.layoutManager = LinearLayoutManager(requireActivity())

        viewModel.observeShowProgress().observe(viewLifecycleOwner, Observer { show ->
            val progress = fragmentBinding.root.fragment_arcade_loading_bar
            if (show) progress.show() else progress.hide()
        })

        viewModel.observeArcades().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        return fragmentBinding.root
    }
}