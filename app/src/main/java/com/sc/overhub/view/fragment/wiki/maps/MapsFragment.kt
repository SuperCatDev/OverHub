package com.sc.overhub.view.fragment.wiki.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.sc.overhub.R
import com.sc.overhub.databinding.FragmentWikiMapsBinding
import com.sc.overhub.view.fragment.BaseFragment
import com.sc.overhub.viewmodel.MapsViewModel
import com.sc.overhub.viewmodel.getViewModel

class MapsFragment : BaseFragment() {
    private val viewModel: MapsViewModel by lazy {
        getViewModel {
            MapsViewModel(
                Navigation.findNavController(
                    activity!!,
                    R.id.wiki_host_fragment
                )
            )
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding: FragmentWikiMapsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_wiki_maps, container, false
        )

        fragmentBinding.viewModel = viewModel

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigation = Navigation.findNavController(
            activity!!,
            R.id.wiki_host_fragment
        )
    }
}