package com.sc.overhub.model

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import com.sc.overhub.entry.WikiHeroOverviewEntry

class WikiHeroOverviewModel: BaseObservable() {
    private var overviewText: MutableLiveData<String> = MutableLiveData()
    private var descriptionList: MutableLiveData<List<WikiHeroOverviewEntry>> = MutableLiveData()

    fun getOverviewText():  MutableLiveData<String> = overviewText

    fun getDescriptionList(): MutableLiveData<List<WikiHeroOverviewEntry>> = descriptionList

    init {
        overviewText.value = "ОПИСАНИЕ 123"
        descriptionList.value = listOf(
            WikiHeroOverviewEntry("Здоровье", "200"),
            WikiHeroOverviewEntry("Здоровье", "200"),
            WikiHeroOverviewEntry("Здоровье", "200"),
            WikiHeroOverviewEntry("Здоровье", "200"),
            WikiHeroOverviewEntry("Здоровье", "200"))
    }
}