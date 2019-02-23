package com.sc.overhub.view.fragment.wiki.herosList.hero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sc.overhub.R
import com.sc.overhub.view.fragment.BaseFragment

class WikiHeroFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_view_hero, container, false)
    }
}