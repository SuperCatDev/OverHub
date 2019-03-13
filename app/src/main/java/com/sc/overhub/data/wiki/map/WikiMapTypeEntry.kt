package com.sc.overhub.data.wiki.map

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "wiki_map_type")
data class WikiMapTypeEntry (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long?,

    @ColumnInfo(name = "type")
    val type: String

)