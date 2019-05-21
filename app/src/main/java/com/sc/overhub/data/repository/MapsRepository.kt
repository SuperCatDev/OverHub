package com.sc.overhub.data.repository

import com.sc.overhub.data.db.AppDataBase
import com.sc.overhub.data.db.WikiMapDao
import com.sc.overhub.domain.mapper.MapMapper
import com.sc.overhub.domain.model.GameMapListModel
import com.sc.overhub.domain.model.GameMapModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MapsRepository {
    // Suspend modifier guarantees that this methods
    // will be called from coroutine and was be controlled
    // by invoker coroutine scope
    suspend fun getMapsForList(): List<GameMapListModel>

    suspend fun getMapInfo(id: Long): GameMapModel
}

class MapsRepositoryImpl(private val wikiMapDao: WikiMapDao, private val mapper: MapMapper) : MapsRepository {

    suspend fun initDefault() = withContext(Dispatchers.IO) {
        wikiMapDao.I_insertMapImage(AppDataBase.PREPOPULATE_DATA_IMAGE)
        wikiMapDao.I_insertTypeMap(AppDataBase.PREPOPULATE_DATA_TYPE)
        wikiMapDao.I_insertStatistics(AppDataBase.PREPOPULATE_DATA_STATS)
        wikiMapDao.I_insert(AppDataBase.PREPOPULATE_DATA)
    }

    override suspend fun getMapsForList(): List<GameMapListModel> = withContext(Dispatchers.IO) {
        // wikiMapDao.insertMapImage(PREPOPULATE_DATA_IMAGE)
        // wikiMapDao.insertTypeMap(PREPOPULATE_DATA_TYPE)
        // wikiMapDao.insertStatistics(PREPOPULATE_DATA_STATS)
        // wikiMapDao.insert(PREPOPULATE_DATA)
        wikiMapDao.getMapsForList().map { mapper.mapTo(it) }
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
