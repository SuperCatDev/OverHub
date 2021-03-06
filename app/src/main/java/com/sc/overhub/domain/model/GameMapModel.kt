package com.sc.overhub.domain.model

data class GameMapModel(
    val id: Long = 0,
    val name: String = "",
    val description: String = "",
    val titleImageID: Int = 0,
    val imagesId: List<Int> = listOf(),
    val statistics: List<String> = listOf(),
    val type: String = "")
