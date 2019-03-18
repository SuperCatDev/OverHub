package com.sc.overhub.data.wiki.hero

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "wiki_hero_skill")
@ForeignKey(entity = WikiHeroEntity::class, parentColumns = ["id"], childColumns = ["hero_id"], onDelete = ForeignKey.CASCADE)
data class WikiHeroSkillEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long?,

    @ColumnInfo(name = "hero_id")
    var idHero: Long,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "res_uri")
    var image: Int
)