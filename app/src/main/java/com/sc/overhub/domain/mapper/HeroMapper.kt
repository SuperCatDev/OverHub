package com.sc.overhub.domain.mapper

import com.sc.overhub.data.db.wiki.WikiHero
import com.sc.overhub.data.db.wiki.WikiHeroForList
import com.sc.overhub.data.db.wiki.hero.WikiHeroOverviewEntity
import com.sc.overhub.data.db.wiki.hero.WikiHeroSkillEntity
import com.sc.overhub.data.db.wiki.hero.WikiHeroSkillExtraEntity
import com.sc.overhub.data.db.wiki.hero.WikiHeroTipEntity
import com.sc.overhub.data.model.WikiHeroSkillExtraModel
import com.sc.overhub.data.model.WikiHeroSkillMainModel
import com.sc.overhub.domain.model.WikiHeroListModel
import com.sc.overhub.domain.model.WikiHeroModel
import com.sc.overhub.domain.model.WikiHeroOverViewModel
import com.sc.overhub.domain.model.WikiHeroTipModel

class HeroMapper {
    fun mapTo(data: WikiHeroForList): WikiHeroListModel {
        return WikiHeroListModel(data.id, data.name, data.role, data.complexity, data.image)
    }

    fun mapTo(data: WikiHeroTipEntity): WikiHeroTipModel{
        return WikiHeroTipModel(data.id!!, data.text )
    }

    fun mapTo(data: WikiHeroSkillEntity): WikiHeroSkillMainModel {
        return WikiHeroSkillMainModel(data.title, data.description, data.image)
    }

    fun mapTo(data: WikiHeroSkillExtraEntity): WikiHeroSkillExtraModel {
        return WikiHeroSkillExtraModel(data.headline, data.description)
    }

    fun mapTo(data: WikiHeroOverviewEntity): WikiHeroOverViewModel{
        return WikiHeroOverViewModel(data.title, data.description)
    }

    fun mapTo(data: WikiHero): WikiHeroModel{
        return WikiHeroModel(data.id, data.name, data.description, data.image, data.role, data.complexity)
    }
}