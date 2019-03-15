package com.sc.overhub.entity

data class WikiHeroesListEntity(
    val id: Long = 0,
    val name: String,
    val role: String,
    val complexity: Int,
    val image: String = "",
    val color: String = ""
)