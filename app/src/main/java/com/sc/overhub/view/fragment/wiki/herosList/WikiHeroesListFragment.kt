package com.sc.overhub.view.fragment.wiki.herosList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sc.overhub.R
import com.sc.overhub.databinding.FragmentWikiListHeroesBinding
import com.sc.overhub.view.fragment.BaseFragment
import com.sc.overhub.viewmodel.WikiHeroListViewModel

class WikiHeroesListFragment : BaseFragment() {
    private lateinit var viewModel: WikiHeroListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(WikiHeroListViewModel::class.java)

        val fragmentBinding: FragmentWikiListHeroesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_wiki_list_heroes, container, false)

        val view =  fragmentBinding.root
        if (savedInstanceState == null){
            viewModel.init()
        }
        fragmentBinding.model = viewModel
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loading!!.set(View.VISIBLE)
        viewModel.getHeroesList().observe(this, Observer {
            viewModel.loading!!.set(View.GONE)
            if (it.size == 0){
                viewModel.showEmpty!!.set(View.VISIBLE)
            } else {
                viewModel.showEmpty!!.set(View.GONE)
                viewModel.setHeroesInAdapter(it)
            }

        })
    }
}