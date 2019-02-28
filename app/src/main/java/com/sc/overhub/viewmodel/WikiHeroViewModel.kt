package com.sc.overhub.viewmodel

import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sc.overhub.entry.WikiHeroOverviewEntry
import com.sc.overhub.entry.WikiHeroSkillEntry
import com.sc.overhub.entry.WikiHeroSkillExtra
import com.sc.overhub.entry.WikiHeroSkillMainEntry
import com.sc.overhub.model.WikiHeroOverviewModel
import com.sc.overhub.model.WikiHeroSkillsModel
import com.sc.overhub.view.adapter.WikiHeroOverviewAdapter
import com.sc.overhub.view.adapter.WikiHeroSkillsAdapter

class WikiHeroViewModel(
    private val overviewModel: WikiHeroOverviewModel,
    private val skillsModel: WikiHeroSkillsModel,
    var loading: ObservableInt,
    var showempty: ObservableInt
) : ViewModel() {

    private var overviewAdapter = WikiHeroOverviewAdapter(this)

    private var skillsAdapter = WikiHeroSkillsAdapter(this)

    fun getOverviewText(): MutableLiveData<String> = overviewModel.getOverviewText()

    fun getDescriptionList(): MutableLiveData<List<WikiHeroOverviewEntry>> = overviewModel.getDescriptionList()

    fun getSkillsList(): MutableLiveData<List<WikiHeroSkillEntry>> = skillsModel.getSkills()

    fun setOverviewInAdapter(descriptionsOverviewList: List<WikiHeroOverviewEntry>) {
        overviewAdapter.setListDescription(descriptionsOverviewList)
        overviewAdapter.notifyDataSetChanged()
    }

    fun setSkillsInAdapter(skills: List<WikiHeroSkillEntry>) {
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

    fun getDescriptionAtIndex(index: Int): WikiHeroOverviewEntry? {
        val value = overviewModel.getDescriptionList().value
        if (value != null && value.size > index) {
            return value[index]
        }
        return null
    }

    fun getMainSkillAtIndex(index: Int): WikiHeroSkillMainEntry? {
        val value = skillsModel.getSkills().value
        if (value != null && value.size > index) {
            return (value[index] as WikiHeroSkillMainEntry)
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
}