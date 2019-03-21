package com.sc.overhub.data.wiki.hero

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "wiki_hero_overview")
@ForeignKey(entity = WikiHeroEntity::class, parentColumns = ["id"], childColumns = ["id_hero"], onDelete = ForeignKey.CASCADE)
data class WikiHeroOverviewEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long?,

    @ColumnInfo(name = "id_hero")
    var heroId: Long,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    val description: String
)