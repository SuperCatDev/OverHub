package com.sc.overhub.model

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import com.sc.overhub.entry.WikiHeroesListEntry

class WikiHeroesListModel : BaseObservable() {

    private var wikiHeroListModelHeroesList: List<WikiHeroesListEntry> = listOf()
    private var heroes: MutableLiveData<List<WikiHeroesListEntry>> = MutableLiveData()

    fun getHeroes(): MutableLiveData<List<WikiHeroesListEntry>> = heroes

    init {
        heroes.value = listOf(
            WikiHeroesListEntry(name = "", role = "", complexity = 1),
            WikiHeroesListEntry(name = "", role = "", complexity = 1),
            WikiHeroesListEntry(name = "", role = "", complexity = 1)
        )
    }
}