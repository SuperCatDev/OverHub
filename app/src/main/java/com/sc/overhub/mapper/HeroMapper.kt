package com.sc.overhub.mapper

import com.sc.overhub.data.wiki.WikiHero
import com.sc.overhub.data.wiki.WikiHeroForList
import com.sc.overhub.data.wiki.hero.WikiHeroOverviewEntity
import com.sc.overhub.data.wiki.hero.WikiHeroSkillEntity
import com.sc.overhub.data.wiki.hero.WikiHeroSkillExtraEntity
import com.sc.overhub.data.wiki.hero.WikiHeroTipEntity
import com.sc.overhub.entity.WikiHeroSkillExtraModel
import com.sc.overhub.entity.WikiHeroSkillMainModel
import com.sc.overhub.model.WikiHeroListModel
import com.sc.overhub.model.WikiHeroModel
import com.sc.overhub.model.WikiHeroOverViewModel
import com.sc.overhub.model.WikiHeroTipModel

class HeroMapper {
    fun mapTo(data: WikiHeroForList): WikiHeroListModel {
        return WikiHeroListModel(data.id, data.name, data.role, data.complexity, data.image)
    }

    fun mapTo(data: WikiHeroTipEntity): WikiHeroTipModel{
        return WikiHeroTipModel(data.id!!, data.text )
    }

    fun mapTo(data: WikiHeroSkillEntity): WikiHeroSkillMainModel{
        return WikiHeroSkillMainModel(data.title, data.description, data.image)
    }

    fun mapTo(data: WikiHeroSkillExtraEntity): WikiHeroSkillExtraModel{
        return WikiHeroSkillExtraModel(data.headline, data.description)
    }

    fun mapTo(data: WikiHeroOverviewEntity): WikiHeroOverViewModel{
        return WikiHeroOverViewModel(data.title, data.description)
    }

    fun mapTo(data: WikiHero): WikiHeroModel{
        return WikiHeroModel(data.id, data.name, data.description, data.image, data.role, data.complexity)
    }
}