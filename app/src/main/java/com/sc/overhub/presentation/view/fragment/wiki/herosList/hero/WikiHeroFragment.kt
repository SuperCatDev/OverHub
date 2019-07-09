package com.sc.overhub.presentation.view.fragment.wiki.herosList.hero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.sc.overhub.R
import com.sc.overhub.databinding.FragmentWikiHeroBinding
import com.sc.overhub.presentation.view.adapter.WikiHeroTabsAdapter
import com.sc.overhub.presentation.view.fragment.BaseFragment
import com.sc.overhub.presentation.viewmodel.WikiHeroViewModel
import com.sc.overhub.presentation.viewmodel.getViewModel
import kotlinx.android.synthetic.main.fragment_wiki_hero.view.*

class WikiHeroFragment : BaseFragment() {

    private lateinit var wikiHeroTabsAdapter: WikiHeroTabsAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var tabsLayout: TabLayout

    private val viewModel: WikiHeroViewModel by lazy {
        getViewModel {
            WikiHeroViewModel(11)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        instance = this
        if (arguments != null) {
            //todo will be used un future
            arguments!!.getString("ARG_HERO_NAME")
        }
        val fragmentBinding =
            DataBindingUtil.inflate<FragmentWikiHeroBinding>(inflater, R.layout.fragment_wiki_hero, container, false)
        val view = fragmentBinding.root
        fragmentBinding.viewModel = viewModel
        fragmentBinding.executePendingBindings()

        viewPager = view.wiki_hero_viewpager
        tabsLayout = view.wiki_hero_tabs
        wikiHeroTabsAdapter = WikiHeroTabsAdapter(childFragmentManager, context!!)
        viewPager.adapter = wikiHeroTabsAdapter
        tabsLayout.setupWithViewPager(viewPager)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        instance = null
    }

    interface WikiHeroFragmentTab {
        fun getHostedViewModel(): WikiHeroViewModel? =
            instance?.viewModel
    }

    companion object {
        private var instance: WikiHeroFragment? = null
    }
}