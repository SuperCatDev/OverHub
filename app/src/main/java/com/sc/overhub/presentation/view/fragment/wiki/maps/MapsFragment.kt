package com.sc.overhub.presentation.view.fragment.wiki.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.sc.overhub.R
import com.sc.overhub.databinding.FragmentWikiMapsBinding
import com.sc.overhub.presentation.view.adapter.MapsListAdapter
import com.sc.overhub.presentation.view.fragment.BaseFragment
import com.sc.overhub.presentation.viewmodel.MapsViewModel
import com.sc.overhub.presentation.viewmodel.getViewModel
import kotlinx.android.synthetic.main.fragment_wiki_maps.view.*

class MapsFragment : BaseFragment() {
    private val viewModel: MapsViewModel by lazy {
        getViewModel {
            MapsViewModel()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding: FragmentWikiMapsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_wiki_maps, container, false
        )

        viewModel.initAdapter.observe(viewLifecycleOwner, Observer {
            fragmentBinding.root.fragment_maps_list.adapter = MapsListAdapter(viewModel)
            fragmentBinding.root.fragment_maps_list.layoutManager = LinearLayoutManager(activity)
        })

        viewModel.navigateToMap.observe(viewLifecycleOwner, Observer {
            val args = Bundle()
            args.putLong("map_id", it)
            Navigation.findNavController(activity!!, R.id.wiki_host_fragment)
                .navigate(R.id.action_mapsFragment_to_mapFragment, args)
        })

        fragmentBinding.lifecycleOwner = viewLifecycleOwner
        fragmentBinding.viewModel = viewModel

        return fragmentBinding.root
    }
}