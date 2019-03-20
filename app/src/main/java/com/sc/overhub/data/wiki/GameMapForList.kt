package com.sc.overhub.data.wiki

import androidx.room.ColumnInfo

data class GameMapForList(
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "res_uri")
    var titleImageID: Int = 0,
    @ColumnInfo(name = "type")
    var type: String = "")