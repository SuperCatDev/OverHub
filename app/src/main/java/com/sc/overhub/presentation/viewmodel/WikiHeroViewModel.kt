package com.sc.overhub.presentation.viewmodel

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sc.overhub.data.model.WikiHeroSkillExtraModel
import com.sc.overhub.data.model.WikiHeroSkillMainModel
import com.sc.overhub.data.model.WikiHeroSkillModel
import com.sc.overhub.data.repository.HeroesRepository
import com.sc.overhub.domain.model.WikiHeroModel
import com.sc.overhub.domain.model.WikiHeroOverViewModel
import com.sc.overhub.domain.model.WikiHeroTipModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.koin.core.KoinComponent
import org.koin.core.inject

class WikiHeroViewModel(private val heroId: Long) : ViewModel(), KoinComponent {
    private val repo: HeroesRepository by inject()

    var loading: ObservableInt = ObservableInt(View.GONE)
    var showEmpty: ObservableInt = ObservableInt(View.GONE)

    private val hero: ObservableField<WikiHeroModel> by lazy {
        ObservableField(runBlocking { heroAsync.await() })
    }

    private val overview: ObservableField<List<WikiHeroOverViewModel>> by lazy {
        ObservableField(runBlocking { overviewAsync.await() })
    }

    private val skills: ObservableField<List<WikiHeroSkillModel>> by lazy {
        ObservableField(runBlocking { skillsAsync.await() })
    }

    private val tips: ObservableField<List<WikiHeroTipModel>> by lazy {
        ObservableField(runBlocking { tipsAsync.await() })
    }

    private val heroAsync = viewModelScope.async(Dispatchers.Default) {
        repo.getHeroById(heroId)
    }

    private val overviewAsync = viewModelScope.async(Dispatchers.Default) {
        repo.getHeroOverview(heroId)
    }

    private val skillsAsync = viewModelScope.async(Dispatchers.Default) {
        repo.getHeroSkills(heroId)
    }

    private val tipsAsync = viewModelScope.async(Dispatchers.Default) {
        repo.getHeroTips(heroId)
    }

    fun getSkills(): List<WikiHeroSkillModel> = skills.get()!!

    fun getOverview(): List<WikiHeroOverViewModel> = overview.get()!!

    fun getDescriptionAtIndex(index: Int) = overview.get()!![index]

    fun getMainSkillAtIndex(index: Int): WikiHeroSkillMainModel? {
        val value = skills.get()
        if (value != null && value.size > index) {
            return (value[index] as WikiHeroSkillMainModel)
        }
        return null
    }

    fun getExtraSkillAtIndex(index: Int): WikiHeroSkillExtraModel? {
        val value = skills.get()
        if (value != null && value.size > index) {
            return (value[index] as WikiHeroSkillExtraModel)
        }
        return null
    }

    fun getOverViewTextAt(): String? {
        val value = hero.get()
        if (value != null) {
            return value.name
        }
        return null
    }

}