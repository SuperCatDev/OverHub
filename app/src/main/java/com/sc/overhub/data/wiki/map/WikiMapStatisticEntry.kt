package com.sc.overhub.data.wiki.map

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "wiki_map_statistic")
@ForeignKey(entity = WikiMapEntry::class, parentColumns = ["id"], childColumns = ["map_id"], onDelete = CASCADE)
data class WikiMapStatisticEntry(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long?,

    @ColumnInfo(name = "map_id")
    var MapId: Long,

    @ColumnInfo(name = "text_statistic")
    var text: String
)