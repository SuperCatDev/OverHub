package com.sc.overhub.model

import androidx.room.ColumnInfo

data class WikiHeroForList(

    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "role")
    val role: String,

    @ColumnInfo(name = "complexity")
    val complexity: Int
)