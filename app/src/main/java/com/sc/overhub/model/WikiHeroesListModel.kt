package com.sc.overhub.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.sc.overhub.entry.WikiHeroesListEntry

class WikiHeroesListModel : BaseObservable() {
    private var heroes: MutableLiveData<List<WikiHeroesListEntry>> = MutableLiveData()

    fun getHeroes(): MutableLiveData<List<WikiHeroesListEntry>> = heroes

    init {
        heroes.value = listOf(
            WikiHeroesListEntry(name = "Hero 1", role = "Tank", complexity = 1),
            WikiHeroesListEntry(name = "Hero 2", role = "Support", complexity = 2),
            WikiHeroesListEntry(name = "Hero 3", role = "DPS", complexity = 3)
        )
    }
}