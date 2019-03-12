package com.sc.overhub.model

data class GameMap(val name: String, val imageId: Int, val type: MapType)

enum class MapType {
    ASSAULT,
    CONTROL,
    ESCORT,
    HYBRID,
    OTHER
}
