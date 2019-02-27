package com.sc.overhub.view.fragment.wiki.herosList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.sc.overhub.R
import com.sc.overhub.databinding.FragmentWikiListHeroesBinding
import com.sc.overhub.model.WikiHeroesListModel
import com.sc.overhub.view.activity.HomeActivity
import com.sc.overhub.view.fragment.BaseFragment
import com.sc.overhub.viewmodel.WikiHeroListViewModel
import com.sc.overhub.viewmodel.getViewModel

class WikiHeroesListFragment : BaseFragment() {
    private val viewModel: WikiHeroListViewModel by lazy {
        getViewModel {
            WikiHeroListViewModel(
                WikiHeroesListModel(),
                R.layout.item_wiki_heroes_list,
                MutableLiveData(),
                ObservableInt(View.GONE),
                ObservableInt(View.GONE),
                with(activity!!) {
                    getViewModel(HomeActivity.getViewModelGetter(this))
                }.navController
            )
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding: FragmentWikiListHeroesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_wiki_list_heroes, container, false
        )

        viewModel.reinitController(with(activity!!) {
            getViewModel(HomeActivity.getViewModelGetter(this))
        }.navController)

        val view = fragmentBinding.root

        fragmentBinding.model = viewModel
        fragmentBinding.executePendingBindings()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loading.set(View.VISIBLE)

        viewModel.getHeroesList().observe(this, Observer {
            viewModel.loading.set(View.GONE)
            if (it.isEmpty()) {
                viewModel.showEmpty.set(View.VISIBLE)
            } else {
                viewModel.showEmpty.set(View.GONE)
                viewModel.setHeroesInAdapter(it)
            }
        })
    }
}