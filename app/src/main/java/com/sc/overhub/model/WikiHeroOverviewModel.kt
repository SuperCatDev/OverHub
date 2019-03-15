package com.sc.overhub.model

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import com.sc.overhub.entity.WikiHeroOverviewEntity

class WikiHeroOverviewModel: BaseObservable() {
    private var overviewText: MutableLiveData<String> = MutableLiveData()
    private var descriptionList: MutableLiveData<List<WikiHeroOverviewEntity>> = MutableLiveData()

    fun getOverviewText():  MutableLiveData<String> = overviewText

    fun getDescriptionList(): MutableLiveData<List<WikiHeroOverviewEntity>> = descriptionList

    init {
        overviewText.value = "ОПИСАНИЕ 123"
        descriptionList.value = listOf(
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"),
            WikiHeroOverviewEntity("Здоровье", "200"))
    }
}