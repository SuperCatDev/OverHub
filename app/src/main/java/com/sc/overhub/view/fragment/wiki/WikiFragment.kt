package com.sc.overhub.view.fragment.wiki

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sc.overhub.R
import com.sc.overhub.view.activity.HomeActivity
import com.sc.overhub.view.fragment.BaseFragment
import com.sc.overhub.viewmodel.getViewModel
import kotlinx.android.synthetic.main.fragment_wiki.*

class WikiFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wiki, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navigationController = with(activity!!) {
            getViewModel(HomeActivity.getViewModelGetter(this))
        }.navController

        fragment_wiki_heroes_image.setOnClickListener {
            navigationController.navigate(R.id.action_wikiFragment_to_wikiHeroesListFragment)
        }

        fragment_wiki_maps_image.setOnClickListener {
            navigationController.navigate(R.id.action_wikiFragment_to_mapsFragment)
        }
    }
}
