package com.sc.overhub.data.db.wiki.hero

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "wiki_hero_tip")
@ForeignKey(entity = WikiHeroEntity::class, parentColumns = ["id"], childColumns = ["hero_id"], onDelete = ForeignKey.CASCADE)
data class WikiHeroTipEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long?,

    @ColumnInfo(name = "hero_id")
    var heroId: Long,

    @ColumnInfo(name = "tip_text")
    var text: String
)