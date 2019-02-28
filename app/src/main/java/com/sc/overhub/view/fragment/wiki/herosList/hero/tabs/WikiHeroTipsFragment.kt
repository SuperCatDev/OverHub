package com.sc.overhub.view.fragment.wiki.herosList.hero.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sc.overhub.R
import com.sc.overhub.databinding.FragmentWikiHeroTipsBinding
import com.sc.overhub.view.fragment.wiki.herosList.hero.WikiHeroFragment

class WikiHeroTipsFragment: WikiHeroFragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding = DataBindingUtil.inflate<FragmentWikiHeroTipsBinding>(
            inflater, R.layout.fragment_wiki_hero_tips, container, false)

        val view = fragmentBinding.root
        fragmentBinding.viewModel = viewModel
        fragmentBinding.executePendingBindings()
        return view
    }

}