package com.sc.overhub.view.fragment.wiki.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sc.overhub.R
import com.sc.overhub.databinding.FragmentWikiMapsBinding
import com.sc.overhub.view.fragment.BaseFragment
import com.sc.overhub.viewmodel.MapsViewModel
import com.sc.overhub.viewmodel.getViewModel

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

        fragmentBinding.viewModel = viewModel

        return fragmentBinding.root
    }
}