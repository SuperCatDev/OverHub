package com.sc.overhub.presentation.view.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sc.overhub.R
import com.sc.overhub.presentation.view.fragment.wiki.herosList.hero.tabs.WikiHeroDescriptionFragment
import com.sc.overhub.presentation.view.fragment.wiki.herosList.hero.tabs.WikiHeroSkillsFragment
import com.sc.overhub.presentation.view.fragment.wiki.herosList.hero.tabs.WikiHeroTipsFragment

class WikiHeroTabsAdapter(fm: FragmentManager, private val context: Context) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> WikiHeroDescriptionFragment()
        1 -> WikiHeroSkillsFragment()
        2 -> WikiHeroTipsFragment()
        else -> Fragment()
    }

    override fun getPageTitle(position: Int): CharSequence? = when (position) {
        0 -> context.getString(R.string.wiki_hero_tab_overview)
        1 -> context.getString(R.string.wiki_hero_tab_skills)
        2 -> context.getString(R.string.wiki_hero_tab_tips)
        else -> null
    }

    companion object {
        private const val PAGE_COUNT = 3
    }
}