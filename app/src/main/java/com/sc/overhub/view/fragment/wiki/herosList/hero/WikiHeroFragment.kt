package com.sc.overhub.view.fragment.wiki.herosList.hero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableInt
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.sc.overhub.R
import com.sc.overhub.model.WikiHeroOverviewModel
import com.sc.overhub.model.WikiHeroSkillsModel
import com.sc.overhub.view.adapters.WikiHeroTabsAdapter
import com.sc.overhub.view.fragment.BaseFragment
import com.sc.overhub.viewmodel.WikiHeroViewModel
import com.sc.overhub.viewmodel.getViewModel
import kotlinx.android.synthetic.main.fragment_view_hero.view.*

class WikiHeroFragment : BaseFragment() {

    private lateinit var wikiHeroTabsAdapter: WikiHeroTabsAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var tabsLayout: TabLayout

    private val viewModel: WikiHeroViewModel by lazy {
        getViewModel {
            WikiHeroViewModel(
                WikiHeroOverviewModel(),
                WikiHeroSkillsModel(),
                ObservableInt(View.GONE),
                ObservableInt(View.GONE)
            )
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (arguments != null){
           val name = arguments!!.getString("ARG_HERO_NAME")
        }
        val fragmentBinding: FragmentWikiHero = DataBindingUtil.inflate(inflater, R.layout.fragment_view_hero, container, false)
        val view = fragmentBinding.root

        fragmentBinding.model = viewModel
        fragmentBinding.executePendingBindings()

        viewPager = view.wiki_hero_viewpager
        tabsLayout = view.wiki_hero_tabs
        wikiHeroTabsAdapter = WikiHeroTabsAdapter(childFragmentManager, context!!)
        viewPager.adapter = wikiHeroTabsAdapter
        tabsLayout.setupWithViewPager(viewPager)
        return  view
    }

}