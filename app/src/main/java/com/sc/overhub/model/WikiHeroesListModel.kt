package com.sc.overhub.model

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import com.sc.overhub.entity.WikiHeroesListEntity

class WikiHeroesListModel : BaseObservable() {
    private var heroes: MutableLiveData<List<WikiHeroesListEntity>> = MutableLiveData()

    fun getHeroes(): MutableLiveData<List<WikiHeroesListEntity>> = heroes

    init {
        heroes.value = listOf(
            WikiHeroesListEntity(name = "Hero 1", role = "Tank", complexity = 1),
            WikiHeroesListEntity(name = "Hero 2", role = "Support", complexity = 2),
            WikiHeroesListEntity(name = "Hero 3", role = "DPS", complexity = 3)
        )
    }
}