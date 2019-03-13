package com.sc.overhub.model

data class GameMap(
    val id: Long = 0,
    val name: String = "",
    val description: String = "",
    val titleImageID: Int = 0,
    val imagesId: List<Int> = listOf(),
    val statistics: List<String> = listOf(),
    val type: String = "")

enum class MapType {
    ASSAULT,
    CONTROL,
    ESCORT,
    HYBRID,
    OTHER
}
