package com.sc.overhub.mapper

import com.sc.overhub.data.wiki.map.WikiMapEntity
import com.sc.overhub.model.GameMapModel

class MapMapper{
    fun mapTo(
        type: WikiMapEntity,
        titleImage: Int,
        images: List<Int>,
        statistics: List<String>,
        typeMap: String
    ): GameMapModel {
        return GameMapModel(type.id!!, type.name, type.description, titleImage, images, statistics, typeMap)
    }
}