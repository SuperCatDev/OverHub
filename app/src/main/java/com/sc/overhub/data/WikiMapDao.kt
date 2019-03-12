package com.sc.overhub.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WikiMapDao {

    @Query("SELECT * from wiki_map")
    suspend fun getAll(): List<WikiMapEntry>

    @Query("SELECT * from wiki_map WHERE id = :mapId")
    suspend fun getById(mapId: Long): List<WikiMapEntry>

    @Insert
    suspend fun insert(data: List<WikiMapEntry>)

    @Query("DELETE FROM wiki_map")
    suspend fun deleteAll()
}