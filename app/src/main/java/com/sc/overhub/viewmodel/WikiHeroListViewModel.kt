package com.sc.overhub.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sc.overhub.R
import com.sc.overhub.model.WikiHeroesListModel
import com.sc.overhub.entry.WikiHeroesListEntry
import com.sc.overhub.view.adapter.WikHeroListAdapter
import androidx.databinding.ObservableInt

class WikiHeroListViewModel: ViewModel() {

    private lateinit var heroes: WikiHeroesListModel
    private lateinit var adapter: WikHeroListAdapter
    private lateinit var selected: MutableLiveData<WikiHeroesListEntry>

    var loading: ObservableInt? = null
    var showEmpty: ObservableInt? = null


    fun init(){
        heroes = WikiHeroesListModel()
        adapter = WikHeroListAdapter(R.layout.item_wiki_heroes_list, this)
        selected = MutableLiveData()
        loading = ObservableInt(View.GONE)
        showEmpty = ObservableInt(View.GONE)

    }

    fun getHeroesList(): MutableLiveData<List<WikiHeroesListEntry>> = heroes.getHeroes()

    fun getAdapter(): WikHeroListAdapter = adapter

    fun setHeroesInAdapter(entryHeroes: List<WikiHeroesListEntry>){
        adapter.setHeroes(entryHeroes)
        adapter.notifyDataSetChanged()
    }

    fun getSelected(): MutableLiveData<WikiHeroesListEntry> = selected

    fun onItemClick(index: Int){
        val hero = getHeroAtIndex(index)
        selected.value =hero
    }

    fun ocClickReload(){

    }

    fun getHeroAtIndex(index: Int): WikiHeroesListEntry?{
        if (heroes.getHeroes().value != null && index != null){
            if (heroes.getHeroes().value!!.size > index){
                return heroes.getHeroes().value!![index]
            }
        }
        return null
    }





}