package com.sc.overhub.model

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import com.sc.overhub.entry.WikiHeroSkillEntry
import com.sc.overhub.entry.WikiHeroSkillExtra
import com.sc.overhub.entry.WikiHeroSkillMainEntry

class WikiHeroSkillsModel: BaseObservable() {
    private var skills: MutableLiveData<List<WikiHeroSkillEntry>> = MutableLiveData()

    fun getSkills(): MutableLiveData<List<WikiHeroSkillEntry>> = skills

    init {
        skills.value = listOf(
            WikiHeroSkillMainEntry("ДЭШ", "описание"),
            WikiHeroSkillExtra("Здоровье", "200"),
            WikiHeroSkillExtra("Здоровье", "200"),
            WikiHeroSkillExtra("Здоровье", "200"),
            WikiHeroSkillExtra("Здоровье", "200"),
            WikiHeroSkillMainEntry("ДЭШ", "описание"),
            WikiHeroSkillExtra("Здоровье", "200"),
            WikiHeroSkillExtra("Здоровье", "200"),
            WikiHeroSkillExtra("Здоровье", "200"),
            WikiHeroSkillExtra("Здоровье", "200"))
    }
}