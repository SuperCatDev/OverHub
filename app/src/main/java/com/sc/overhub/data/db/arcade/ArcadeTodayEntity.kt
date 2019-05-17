package com.sc.overhub.data.db.arcade

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "arcade_today")
@ForeignKey(entity = ArcadeModeEntity::class, parentColumns = ["id"], childColumns = ["id_large", "id_weekly_1", "id_daily", "id_weekly_2", "id_permanent"])
class ArcadeTodayEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long?,

    @ColumnInfo(name = "id_large")
    var largeId: Long,

    @ColumnInfo(name = "id_weekly_1")
    var weeklyId1: Long,

    @ColumnInfo(name = "id_daily")
    var dailyId: Long,

    @ColumnInfo(name = "id_weekly_2")
    var weeklyId2: Long,

    @ColumnInfo(name = "id_permanent")
    var permanentId: Long,

    @ColumnInfo(name = "update_date")
    var updateAt: String
)