package com.sc.overhub.data.db.wiki.map

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "wiki_map_statistic")
@ForeignKey(entity = WikiMapEntity::class, parentColumns = ["id"], childColumns = ["map_id"], onDelete = CASCADE)
data class WikiMapStatisticEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long?,

    @ColumnInfo(name = "map_id")
    var MapId: Long,

    @ColumnInfo(name = "text_statistic")
    var text: String
)