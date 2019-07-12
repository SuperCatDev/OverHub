package com.sc.overhub.presentation.view.fragment.wiki.herosList.hero.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.sc.overhub.R
import com.sc.overhub.databinding.FragmentWikiHeroOverviewBinding
import com.sc.overhub.presentation.view.adapter.WikiHeroOverviewAdapter
import com.sc.overhub.presentation.view.fragment.BaseFragment
import com.sc.overhub.presentation.view.fragment.wiki.herosList.hero.WikiHeroFragment
import kotlinx.android.synthetic.main.fragment_wiki_hero_overview.view.*

class WikiHeroDescriptionFragment : BaseFragment(), WikiHeroFragment.WikiHeroFragmentTab {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding = DataBindingUtil.inflate<FragmentWikiHeroOverviewBinding>(
            inflater, R.layout.fragment_wiki_hero_overview, container, false
        )
        val view = fragmentBinding.root

        getHostedViewModel()?.also {
            fragmentBinding.viewModel = it
            view.fragment_wiki_hero_overview_recycler.adapter = WikiHeroOverviewAdapter(it)
            view.fragment_wiki_hero_overview_recycler.layoutManager = LinearLayoutManager(activity)
        }

        fragmentBinding.executePendingBindings()
        return view
    }

}