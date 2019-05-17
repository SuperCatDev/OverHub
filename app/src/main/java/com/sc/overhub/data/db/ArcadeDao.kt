package com.sc.overhub.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sc.overhub.data.db.arcade.ArcadeModeEntity
import com.sc.overhub.data.db.arcade.ArcadeTodayEntity

@Dao
interface ArcadeDao {

    @Query("SELECT * FROM arcade_mode WHERE id = :modeId")
    suspend fun getModeArcadeById(modeId: Long): ArcadeModeEntity?

    @Query("SELECT * FROM arcade_today")
    suspend fun getTodayArcade(): ArcadeTodayEntity?

    @Query("SELECT arcade_today.update_date FROM arcade_today")
    suspend fun getLastUpdateTime(): String?

    @Insert
    fun I_insertTodayArcade(data: ArcadeTodayEntity)

    @Insert
    fun I_insertModeArcade(data: List<ArcadeModeEntity>)
}