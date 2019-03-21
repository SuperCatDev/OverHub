package com.sc.overhub.model

data class WikiHeroModel(
    val id: Long = 0,
    val name: String = "",
    val description: String = "",
    val image: Int = 0,
    val role: String = "",
    val complexity: Int = 0
)