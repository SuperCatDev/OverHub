package com.sc.overhub.view.fragment.wiki.herosList.hero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.sc.overhub.R
import com.sc.overhub.view.adapters.WikiHeroTabsAdapter
import com.sc.overhub.view.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_view_hero.view.*

class WikiHeroFragment : BaseFragment() {

    private lateinit var wikiHeroTabsAdapter: WikiHeroTabsAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var tabsLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (arguments != null){
           val name = arguments!!.getString("ARG_HERO_NAME")
        }
        val view =  inflater.inflate(R.layout.fragment_view_hero, container, false)
        viewPager = view.wiki_hero_viewpager
        tabsLayout = view.wiki_hero_tabs
        wikiHeroTabsAdapter = WikiHeroTabsAdapter(childFragmentManager, context!!)
        viewPager.adapter = wikiHeroTabsAdapter
        tabsLayout.setupWithViewPager(viewPager)
        return  view
    }

}