package com.sc.overhub.data.wiki.map

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "wiki_map")
@ForeignKey(entity = WikiMapTypeEntry::class, parentColumns = ["id"], childColumns = ["id_map_type"], onDelete = ForeignKey.CASCADE)
data class WikiMapEntry(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long?,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "id_map_type")
    var mapTypeId: Long
)