package com.sc.overhub.data.db.wiki.hero

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wiki_hero_role")
data class WikiHeroRoleEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long?,

    @ColumnInfo(name = "role")
    var role: String
)