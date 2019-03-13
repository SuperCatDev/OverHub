package com.sc.overhub.data.wiki.map

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "wiki_map_image")
@ForeignKey(entity = WikiMapEntry::class, parentColumns = ["id"], childColumns = ["map_id"], onDelete = ForeignKey.CASCADE)
data class WikiMapImageEntry(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long?,

    @ColumnInfo(name = "map_id")
    var mapID: Int,

    @ColumnInfo(name = "res_uri")
    var resourceId: Int,

    @ColumnInfo(name = "is_title")
    var isTitle: Boolean = false
)