package com.sc.overhub.presentation.view.fragment.wiki.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sc.overhub.R
import com.sc.overhub.databinding.FragmentMapBinding
import com.sc.overhub.presentation.view.adapter.MapImagesAdapter
import com.sc.overhub.presentation.view.fragment.BaseFragment
import com.sc.overhub.presentation.viewmodel.MapViewModel
import com.sc.overhub.presentation.viewmodel.getViewModel
import kotlinx.android.synthetic.main.fragment_map.view.*

class MapFragment : BaseFragment() {
    private val viewModel: MapViewModel by lazy {
        getViewModel {
            MapViewModel(arguments?.getLong("map_id") ?: 0L)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding: FragmentMapBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_map, container, false
        )

        viewModel.imagesId.observe(viewLifecycleOwner, Observer {
            fragmentBinding.root.map_images.adapter = MapImagesAdapter(it)
            fragmentBinding.root.map_images.layoutManager = LinearLayoutManager(activity)
        })

        fragmentBinding.lifecycleOwner = viewLifecycleOwner
        fragmentBinding.viewModel = viewModel

        return fragmentBinding.root
    }
}