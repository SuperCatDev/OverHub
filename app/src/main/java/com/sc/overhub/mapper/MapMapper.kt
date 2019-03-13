package com.sc.overhub.mapper

import com.sc.overhub.data.wiki.map.WikiMapEntry
import com.sc.overhub.model.GameMap
import com.sc.overhub.model.MapType

class MapMapper: MapperExt<GameMap, WikiMapEntry> {
    override fun mapTo(type: WikiMapEntry): GameMap {
        //TODO: В отдельную табилцу тип карт
        return GameMap(0, "", "", 0, listOf(), listOf(), "")
    }
}