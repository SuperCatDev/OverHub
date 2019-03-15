package com.sc.overhub.viewmodel

import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.sc.overhub.entity.WikiHeroOverviewEntity
import com.sc.overhub.entity.WikiHeroSkillEntity
import com.sc.overhub.entity.WikiHeroSkillExtra
import com.sc.overhub.entity.WikiHeroSkillMainEntity
import com.sc.overhub.model.WikiHeroOverviewModel
import com.sc.overhub.model.WikiHeroSkillsModel
import com.sc.overhub.view.adapter.WikiHeroOverviewAdapter
import com.sc.overhub.view.adapter.WikiHeroSkillsAdapter

class WikiHeroViewModel(
    private val overviewModel: WikiHeroOverviewModel,
    private val skillsModel: WikiHeroSkillsModel,
    var loading: ObservableInt,
    
    var showEmpty: ObservableInt
) : ScopedViewModel() {

    var overviewAdapter = WikiHeroOverviewAdapter(this)

    var skillsAdapter = WikiHeroSkillsAdapter(this)

    fun getOverviewText(): MutableLiveData<String> = overviewModel.getOverviewText()

    fun getDescriptionList(): MutableLiveData<List<WikiHeroOverviewEntity>> = overviewModel.getDescriptionList()

    fun getSkillsList(): MutableLiveData<List<WikiHeroSkillEntity>> = skillsModel.getSkills()

    fun setOverviewInAdapter(descriptionsOverviewList: List<WikiHeroOverviewEntity>) {
        overviewAdapter.setListDescription(descriptionsOverviewList)
        overviewAdapter.notifyDataSetChanged()
    }

    fun setSkillsInAdapter(skills: List<WikiHeroSkillEntity>) {
        skillsAdapter.setSkills(skills)
        skillsAdapter.notifyDataSetChanged()

    }

    fun getOverviewTextAt(): String? {
        val value = overviewModel.getOverviewText().value
        if (value != null) {
            return value
        }
        return null
    }

    fun getDescriptionAtIndex(index: Int): WikiHeroOverviewEntity? {
        val value = overviewModel.getDescriptionList().value
        if (value != null && value.size > index) {
            return value[index]
        }
        return null
    }

    fun getMainSkillAtIndex(index: Int): WikiHeroSkillMainEntity? {
        val value = skillsModel.getSkills().value
        if (value != null && value.size > index) {
            return (value[index] as WikiHeroSkillMainEntity)
        }
        return null
    }

    fun getExtraSkillAtIndex(index: Int): WikiHeroSkillExtra? {
        val value = skillsModel.getSkills().value
        if (value != null && value.size > index) {
            return (value[index] as WikiHeroSkillExtra)
        }
        return null
    }

    fun getOverViewTextAt(): String?{
        val value = getOverviewText().value
        if (value != null){
            return value
        }
        return ""
    }
}