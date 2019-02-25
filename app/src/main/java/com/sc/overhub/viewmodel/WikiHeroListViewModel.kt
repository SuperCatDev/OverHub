package com.sc.overhub.viewmodel

import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.sc.overhub.R
import com.sc.overhub.entry.WikiHeroesListEntry
import com.sc.overhub.model.WikiHeroesListModel
import com.sc.overhub.view.adapter.WikHeroListAdapter

class WikiHeroListViewModel(
    private val heroes: WikiHeroesListModel,
    layoutId: Int,
    var selected: MutableLiveData<WikiHeroesListEntry>,
    var loading: ObservableInt,
    var showEmpty: ObservableInt,
    private val navController: NavController
) : ViewModel() {
    var adapter: WikHeroListAdapter = WikHeroListAdapter(layoutId, this)

    fun getHeroesList(): MutableLiveData<List<WikiHeroesListEntry>> = heroes.getHeroes()

    fun setHeroesInAdapter(entryHeroes: List<WikiHeroesListEntry>) {
        adapter.setHeroes(entryHeroes)
        adapter.notifyDataSetChanged()
    }

    fun onItemClick(index: Int) {
        val hero = getHeroAtIndex(index)
        selected.value = hero
        navController.navigate(R.id.action_wikiHeroesListFragment_to_wikiHeroFragment)
    }

    fun onClickReload() {
    }

    fun getHeroAtIndex(index: Int): WikiHeroesListEntry? {
        if (heroes.getHeroes().value != null) {
            if (heroes.getHeroes().value!!.size > index) {
                return heroes.getHeroes().value!![index]
            }
        }
        return null
    }
}