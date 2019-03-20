package com.sc.overhub.viewmodel

import android.view.View
import androidx.databinding.ObservableInt
import com.sc.overhub.model.WikiHeroListModel
import com.sc.overhub.repository.HeroesRepository
import com.sc.overhub.view.adapter.WikHeroListAdapter
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class WikiHeroListViewModel(
    var navigate: (Long) -> Unit) : ScopedViewModel(), KoinComponent {

    private val repo: HeroesRepository by inject()

    var loading: ObservableInt = ObservableInt(View.GONE)
    var showEmpty: ObservableInt = ObservableInt(View.GONE)
    private val heroes: List<WikiHeroListModel> by lazy { runBlocking { heroesAsync.await() } }

    private val heroesAsync = async { repo.getHeroesForList() }

    var adapter: WikHeroListAdapter = WikHeroListAdapter(this)

    fun getHeroAtIndex(position: Int): WikiHeroListModel = heroes[position]

    fun onItemClick(position: Int){
        navigate(heroes[position].id)
    }

    fun getSize(): Int = heroes.size

    fun onClickReload(){

    }
}