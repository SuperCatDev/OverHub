package com.sc.overhub.repository

import com.sc.overhub.R
import com.sc.overhub.data.WikiMapDao
import com.sc.overhub.mapper.MapMapper
import com.sc.overhub.model.GameMap
import com.sc.overhub.model.GameMapForListModel
import com.sc.overhub.model.MapType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MapsRepository {
    // Suspend modifier guarantees that this methods
    // will be called from coroutine and was be controlled
    // by invoker coroutine scope
    suspend fun getMapsForList(): List<GameMapForListModel>
    suspend fun getMapInfo(id: Long): GameMap
}


class MapsRepositoryImpl(private val wikiMapDao: WikiMapDao, private val mapper: MapMapper) : MapsRepository {


    override suspend fun getMapsForList(): List<GameMapForListModel> = withContext(Dispatchers.IO) {
        /*
        var maps = wikiMapDao.getMapsForList()
        maps.map {
            it.type =  wikiMapDao.getTypeMap(wikiMapDao.getById(it.id).mapTypeId)
            it.titleImageID = wikiMapDao.getTitleImageById(it.id)
        }*/
        val maps = listOf<GameMapForListModel>()
        return@withContext maps
    }

    override suspend fun getMapInfo(id: Long): GameMap = withContext(Dispatchers.IO) {
        /*
        val map = wikiMapDao.getById(id)
        return@withContext GameMap(
            map.id!!,
            map.name,
            map.description,
            wikiMapDao.getTitleImageById(map.id!!),
            wikiMapDao.getImagesById(map.id!!),
            wikiMapDao.getStatisticsById(map.id!!),
            wikiMapDao.getTypeMap(map.mapTypeId)
        )*/
        GameMap()
    }
}
