package com.sc.overhub.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sc.overhub.data.wiki.map.WikiMapEntry
import com.sc.overhub.data.wiki.map.WikiMapImageEntry
import com.sc.overhub.data.wiki.map.WikiMapStatisticEntry
import com.sc.overhub.data.wiki.map.WikiMapTypeEntry
import com.sc.overhub.model.GameMapForListModel

@Dao
interface WikiMapDao {

    @Query("SELECT wiki_map.id, wiki_map.name, wiki_map_image.res_uri, wiki_map_type.type FROM wiki_map INNER JOIN wiki_map_image ON wiki_map.id = wiki_map_image.map_id AND wiki_map_image.is_title = 1 INNER JOIN wiki_map_type ON wiki_map.id_map_type = wiki_map_type.id")
    suspend fun getMapsForList(): List<GameMapForListModel>

    @Query("SELECT * from wiki_map WHERE id = :mapId")
    suspend fun getById(mapId: Long): WikiMapEntry

    @Query("SELECT res_uri from wiki_map_image WHERE map_id = :mapId")
    suspend fun getImagesById(mapId: Long): List<Int>

    @Query("SELECT res_uri from wiki_map_image WHERE map_id = :mapId AND is_title = 1")
    suspend fun getTitleImageById(mapId: Long): Int

    @Query("SELECT text_statistic from wiki_map_statistic WHERE map_id = :mapId")
    suspend fun getStatisticsById(mapId: Long): List<String>

    @Query("SELECT type from wiki_map_type WHERE id = :typeID")
    suspend fun getTypeMap(typeID: Long): String

    @Insert
    suspend fun insert(data: List<WikiMapEntry>)

    @Insert
    suspend fun insertTypeMap(data: List<WikiMapTypeEntry>)

    @Insert
    suspend fun insertMapImage(data: List<WikiMapImageEntry>)

    @Insert
    suspend fun insertStatistics(data: List<WikiMapStatisticEntry>)

    @Query("DELETE FROM wiki_map")
    suspend fun deleteAll()
}