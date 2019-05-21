package com.sc.overhub.presentation.viewmodel

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sc.overhub.domain.model.WikiHeroListModel
import com.sc.overhub.data.repository.HeroesRepository
import com.sc.overhub.presentation.view.adapter.WikHeroListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class WikiHeroListViewModel(
    var navigate: (Long) -> Unit) : ViewModel(), KoinComponent {

    private val repo: HeroesRepository by inject()

    var loading: ObservableInt = ObservableInt(View.GONE)
    var showEmpty: ObservableInt = ObservableInt(View.GONE)
    private val heroes: List<WikiHeroListModel> by lazy { runBlocking { heroesAsync.await() } }

    private val heroesAsync = viewModelScope.async(Dispatchers.Default) { repo.getHeroesForList() }

    var adapter: WikHeroListAdapter = WikHeroListAdapter(this)

    fun getHeroAtIndex(position: Int): WikiHeroListModel = heroes[position]

    fun onItemClick(position: Int){
        navigate(heroes[position].id)
    }

    fun getSize(): Int = heroes.size

    fun onClickReload(){

    }
}