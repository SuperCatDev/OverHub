package com.sc.overhub.repository

import com.sc.overhub.data.AppDataBase
import com.sc.overhub.data.WikiMapDao
import com.sc.overhub.mapper.MapMapper
import com.sc.overhub.model.GameMapModel
import com.sc.overhub.model.GameMapForListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MapsRepository {
    // Suspend modifier guarantees that this methods
    // will be called from coroutine and was be controlled
    // by invoker coroutine scope
    suspend fun getMapsForList(): List<GameMapForListModel>

    suspend fun getMapInfo(id: Long): GameMapModel
}


class MapsRepositoryImpl(private val wikiMapDao: WikiMapDao, private val mapper: MapMapper) : MapsRepository {

    suspend fun initDefault() = withContext(Dispatchers.IO) {
        wikiMapDao.I_insertMapImage(AppDataBase.PREPOPULATE_DATA_IMAGE)
        wikiMapDao.I_insertTypeMap(AppDataBase.PREPOPULATE_DATA_TYPE)
        wikiMapDao.I_insertStatistics(AppDataBase.PREPOPULATE_DATA_STATS)
        wikiMapDao.I_insert(AppDataBase.PREPOPULATE_DATA)
    }

    override suspend fun getMapsForList(): List<GameMapForListModel> = withContext(Dispatchers.IO) {
        // wikiMapDao.insertMapImage(PREPOPULATE_DATA_IMAGE)
        // wikiMapDao.insertTypeMap(PREPOPULATE_DATA_TYPE)
        // wikiMapDao.insertStatistics(PREPOPULATE_DATA_STATS)
        // wikiMapDao.insert(PREPOPULATE_DATA)
        wikiMapDao.getMapsForList()
    }

    override suspend fun getMapInfo(id: Long): GameMapModel = withContext(Dispatchers.IO) {
        val map = wikiMapDao.getById(id)

        return@withContext mapper.mapTo(
            map,
            wikiMapDao.getTitleImageById(id),
            wikiMapDao.getImagesById(id),
            wikiMapDao.getStatisticsById(id),
            wikiMapDao.getTypeMap(map.mapTypeId)
        )
    }
}
