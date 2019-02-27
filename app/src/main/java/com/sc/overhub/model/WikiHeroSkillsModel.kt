package com.sc.overhub.model

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import com.sc.overhub.entry.WikiHeroSkillEntry

class WikiHeroSkillsModel: BaseObservable() {
    private var skills: MutableLiveData<List<WikiHeroSkillEntry>> = MutableLiveData()

    fun getSkills(): MutableLiveData<List<WikiHeroSkillEntry>> = skills

    init {
        //TODO: ВКИНУТЬ ДАННЫЕ
    }
}