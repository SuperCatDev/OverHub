package com.sc.overhub.mapper

import com.sc.overhub.data.wiki.WikiHeroForList
import com.sc.overhub.model.WikiHeroListModel

class HeroMapper {
    fun mapTo(data: WikiHeroForList): WikiHeroListModel {
        return WikiHeroListModel(data.id, data.name, data.role, data.complexity, data.image)
    }
}