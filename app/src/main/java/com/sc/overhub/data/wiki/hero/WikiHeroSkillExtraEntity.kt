package com.sc.overhub.data.wiki.hero

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "wiki_hero_skill_extra")
@ForeignKey(entity = WikiHeroSkillEntity::class, parentColumns = ["id"], childColumns = ["id_skills"], onDelete = ForeignKey.CASCADE)
data class WikiHeroSkillExtraEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long?,

    @ColumnInfo(name = "id_skill")
    var skillId: Long,

    @ColumnInfo(name = "headline")
    var headline: String,

    @ColumnInfo(name = "description")
    var description: String


)