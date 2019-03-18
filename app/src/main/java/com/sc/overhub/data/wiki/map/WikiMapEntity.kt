package com.sc.overhub.data.wiki.map

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "wiki_map")
@ForeignKey(entity = WikiMapTypeEntity::class, parentColumns = ["id"], childColumns = ["id_map_type"])
data class WikiMapEntity(
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