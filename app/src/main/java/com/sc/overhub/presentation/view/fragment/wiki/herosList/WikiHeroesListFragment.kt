package com.sc.overhub.presentation.view.fragment.wiki.herosList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.sc.overhub.R
import com.sc.overhub.databinding.FragmentWikiListHeroesBinding
import com.sc.overhub.presentation.view.fragment.BaseFragment
import com.sc.overhub.presentation.viewmodel.WikiHeroListViewModel
import com.sc.overhub.presentation.viewmodel.getViewModel

class WikiHeroesListFragment : BaseFragment() {
    private val viewModel: WikiHeroListViewModel by lazy {
        getViewModel {
            WikiHeroListViewModel(navigate)
        }
    }

    private val navigate: (Long) -> Unit = {
        val args = Bundle()
        args.putLong("hero_id", it)
        Navigation.findNavController(activity!!, R.id.wiki_host_fragment)
            .navigate(R.id.action_wikiHeroesListFragment_to_wikiHeroFragment, args)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding: FragmentWikiListHeroesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_wiki_list_heroes, container, false
        )

        viewModel.navigate = navigate
        fragmentBinding.model = viewModel
        return fragmentBinding.root
    }
}