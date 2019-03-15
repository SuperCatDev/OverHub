package com.sc.overhub.viewmodel

import android.os.Bundle
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.sc.overhub.entity.WikiHeroesListEntity
import com.sc.overhub.model.WikiHeroesListModel
import com.sc.overhub.view.adapter.WikHeroListAdapter

class WikiHeroListViewModel(
    private val heroes: WikiHeroesListModel,
    layoutId: Int,
    var selected: MutableLiveData<WikiHeroesListEntity>,
    var loading: ObservableInt,
    var showEmpty: ObservableInt,
    var navigate: (Bundle) -> Unit
) : ScopedViewModel() {
    var adapter: WikHeroListAdapter = WikHeroListAdapter(layoutId, this)

    fun getHeroesList(): MutableLiveData<List<WikiHeroesListEntity>> = heroes.getHeroes()

    fun setHeroesInAdapter(entityHeroes: List<WikiHeroesListEntity>) {
        adapter.setHeroes(entityHeroes)
        adapter.notifyDataSetChanged()
    }

    fun onItemClick(index: Int) {
        val hero = getHeroAtIndex(index)
        selected.value = hero
        val arg = Bundle()
        if (hero != null) {
            arg.putLong("ARG_HERO_ID", hero.id)
            arg.putString("ARG_HERO_NAME", hero.name)
        }
        navigate(arg)
    }

    fun onClickReload() {
    }

    fun getHeroAtIndex(index: Int): WikiHeroesListEntity? {
        if (heroes.getHeroes().value != null) {
            if (heroes.getHeroes().value!!.size > index) {
                return heroes.getHeroes().value!![index]
            }
        }
        return null
    }
}