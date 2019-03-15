package com.sc.overhub.model

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import com.sc.overhub.entity.WikiHeroSkillEntity
import com.sc.overhub.entity.WikiHeroSkillExtra
import com.sc.overhub.entity.WikiHeroSkillMainEntity

class WikiHeroSkillsModel: BaseObservable() {
    private var skills: MutableLiveData<List<WikiHeroSkillEntity>> = MutableLiveData()

    fun getSkills(): MutableLiveData<List<WikiHeroSkillEntity>> = skills

    init {
        skills.value = listOf(
            WikiHeroSkillMainEntity("ДЭШ", "описание"),
            WikiHeroSkillExtra("Здоровье", "200"),
            WikiHeroSkillExtra("Здоровье", "200"),
            WikiHeroSkillExtra("Здоровье", "200"),
            WikiHeroSkillExtra("Здоровье", "200"),
            WikiHeroSkillMainEntity("ДЭШ", "описание"),
            WikiHeroSkillExtra("Здоровье", "200"),
            WikiHeroSkillExtra("Здоровье", "200"),
            WikiHeroSkillExtra("Здоровье", "200"),
            WikiHeroSkillExtra("Здоровье", "200"))
    }
}