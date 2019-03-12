package com.sc.overhub.repository

import com.sc.overhub.R
import com.sc.overhub.data.AppDataBase.Companion.PREPOPULATE_DATA
import com.sc.overhub.data.WikiMapDao
import com.sc.overhub.data.WikiMapEntry
import com.sc.overhub.mapper.MapMapper
import com.sc.overhub.model.GameMap
import kotlinx.coroutines.*

interface MapsRepository {
    suspend fun getMapsAsync(): Deferred<List<GameMap>>
}

class MapsRepositoryImpl (private val wikiMapDao: WikiMapDao, private val mapper: MapMapper): MapsRepository {
    override suspend fun getMapsAsync(): Deferred<List<GameMap>> = withContext(Dispatchers.IO) {
        async {
            wikiMapDao.deleteAll() 
            wikiMapDao.insert(PREPOPULATE_DATA)
            val q = wikiMapDao.getAll().map { mapper.mapTo(it) }
            return@async q
        }
    }
}