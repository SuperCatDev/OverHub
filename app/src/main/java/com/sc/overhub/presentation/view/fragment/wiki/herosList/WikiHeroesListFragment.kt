package com.sc.overhub.presentation.view.fragment.wiki.herosList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.sc.overhub.R
import com.sc.overhub.databinding.FragmentWikiListHeroesBinding
import com.sc.overhub.presentation.view.adapter.WikHeroListAdapter
import com.sc.overhub.presentation.view.fragment.BaseFragment
import com.sc.overhub.presentation.viewmodel.WikiHeroListViewModel
import com.sc.overhub.presentation.viewmodel.getViewModel
import kotlinx.android.synthetic.main.fragment_wiki_list_heroes.view.*

class WikiHeroesListFragment : BaseFragment() {
    private val viewModel: WikiHeroListViewModel by lazy {
        getViewModel {
            WikiHeroListViewModel()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding: FragmentWikiListHeroesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_wiki_list_heroes, container, false
        )

        fragmentBinding.root.fragment_wiki_list_heroes_recycler.adapter = WikHeroListAdapter(viewModel)
        fragmentBinding.root.fragment_wiki_list_heroes_recycler.layoutManager = LinearLayoutManager(activity)

        viewModel.navigateToHero.observe(viewLifecycleOwner, Observer {
            val args = Bundle()
            args.putLong("hero_id", it)
            Navigation.findNavController(activity!!, R.id.wiki_host_fragment)
                .navigate(R.id.action_wikiHeroesListFragment_to_wikiHeroFragment, args)
        })

        fragmentBinding.model = viewModel

        return fragmentBinding.root
    }
}