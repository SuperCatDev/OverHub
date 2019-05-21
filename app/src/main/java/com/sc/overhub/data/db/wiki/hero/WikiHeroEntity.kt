package com.sc.overhub.data.db.wiki.hero

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "wiki_hero")
@ForeignKey(entity = WikiHeroRoleEntity::class, parentColumns = ["id"], childColumns = ["id_role"] )
data class WikiHeroEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long?,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "res_uri")
    var image: Int,

    @ColumnInfo(name = "complexity")
    var complexity: Int,

    @ColumnInfo(name = "id_role")
    var roleId: Long
)