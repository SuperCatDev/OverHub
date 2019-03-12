package com.sc.overhub.view.fragment.wiki.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sc.overhub.R
import com.sc.overhub.databinding.FragmentMapBinding
import com.sc.overhub.view.fragment.BaseFragment
import com.sc.overhub.viewmodel.MapViewModel
import com.sc.overhub.viewmodel.getViewModel

class MapFragment : BaseFragment() {
    private val viewModel: MapViewModel by lazy {
        getViewModel {
            MapViewModel(1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding: FragmentMapBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_map, container, false
        )

        fragmentBinding.viewModel = viewModel

        return fragmentBinding.root
    }
}