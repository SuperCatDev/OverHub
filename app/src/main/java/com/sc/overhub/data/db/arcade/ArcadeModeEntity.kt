package com.sc.overhub.data.db.arcade

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "arcade_mode")
data class ArcadeModeEntity (

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "players")
    var playersMode: String,

    @ColumnInfo(name = "code")
    var stringCode: String,

    @ColumnInfo(name = "image_large")
    var imageLarge: String,

    @ColumnInfo(name = "image_normal")
    var imageNormal: String
)