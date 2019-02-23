package com.sc.overhub.view.fragment.wiki.herosList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.sc.overhub.R
import com.sc.overhub.view.fragment.BaseFragment
import com.sc.overhub.view.fragment.wiki.herosList.hero.WikHeroListAdapter
import kotlinx.android.synthetic.main.fragment_wiki_list_heroes.*

class WikiHeroesListFragment : BaseFragment() {
    private lateinit var adapter: WikHeroListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wiki_list_heroes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = WikHeroListAdapter(Navigation.findNavController(activity!!, R.id.nav_host_fragment))
        fragment_wiki_list_heroes_recycler.adapter = adapter
        fragment_wiki_list_heroes_recycler.layoutManager = LinearLayoutManager(view.context)
    }
}