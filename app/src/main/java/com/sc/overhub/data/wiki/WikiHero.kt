package com.sc.overhub.data.wiki

import androidx.room.ColumnInfo

data class WikiHero(

    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "res_uri")
    val image: Int,

    @ColumnInfo(name = "complexity")
    val complexity: Int,

    @ColumnInfo(name = "role")
    val role: String
)