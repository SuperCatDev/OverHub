package com.sc.overhub.view.fragment.wiki.herosList.hero.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sc.overhub.R
import com.sc.overhub.databinding.FragmentWikiHeroSkillsBinding
import com.sc.overhub.view.fragment.wiki.herosList.hero.WikiHeroFragment

class WikiHeroSkillsFragment: WikiHeroFragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding = DataBindingUtil.inflate<FragmentWikiHeroSkillsBinding>(
            inflater, R.layout.fragment_wiki_hero_skills, container, false)
        val view = fragmentBinding.root
        fragmentBinding.viewModel = viewModel
        fragmentBinding.executePendingBindings()
        return view
    }

}