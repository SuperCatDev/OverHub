package com.sc.overhub.data

import androidx.room.Dao
import androidx.room.Query
import com.sc.overhub.data.arcade.ArcadeModeEntity
import com.sc.overhub.data.arcade.ArcadeTodayEntity

@Dao
interface ArcadeDao {

    @Query("SELECT * FROM arcade_mode WHERE id = :modeId")
    suspend fun getModeArcadeById(modeId: Long): ArcadeModeEntity

    @Query("SELECT * FROM arcade_today")
    suspend fun getTodayArcade(): ArcadeTodayEntity
}