package com.sc.overhub.entry

data class WikiHeroesListEntry(
    val id: Long = 0,
    val name: String,
    val role: String,
    val complexity: Int,
    val image: String = "",
    val color: String = ""
)