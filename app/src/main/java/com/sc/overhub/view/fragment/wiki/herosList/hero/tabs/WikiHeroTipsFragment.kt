package com.sc.overhub.view.fragment.wiki.herosList.hero.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sc.overhub.R
import com.sc.overhub.view.fragment.BaseFragment

class WikiHeroTipsFragment: BaseFragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_wiki_hero_tips, container, false)
        return view
    }

}