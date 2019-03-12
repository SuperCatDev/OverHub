package com.sc.overhub.mapper

import com.sc.overhub.data.WikiMapEntry
import com.sc.overhub.model.GameMap

class MapMapper: MapperExt<GameMap, WikiMapEntry> {
    override fun mapTo(type: WikiMapEntry): GameMap {
        return GameMap(name = type.name, imageId = type.imageId)
    }
}