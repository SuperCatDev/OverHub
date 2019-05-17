package com.sc.overhub.domain.mapper

import com.sc.overhub.data.db.wiki.GameMapForList
import com.sc.overhub.data.db.wiki.map.WikiMapEntity
import com.sc.overhub.domain.model.GameMapListModel
import com.sc.overhub.domain.model.GameMapModel

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

    fun mapTo(data: GameMapForList) : GameMapListModel{
        return GameMapListModel(data.id, data.name, data.titleImageID, data.type)
    }
}