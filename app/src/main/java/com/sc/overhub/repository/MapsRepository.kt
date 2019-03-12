package com.sc.overhub.repository

import com.sc.overhub.R
import com.sc.overhub.data.AppDataBase.Companion.PREPOPULATE_DATA
import com.sc.overhub.data.WikiMapDao
import com.sc.overhub.data.WikiMapEntry
import com.sc.overhub.mapper.MapMapper
import com.sc.overhub.model.GameMap
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

interface MapsRepository {
    suspend fun getMapsAsync(): Deferred<List<GameMap>>
}

class MapsRepositoryImpl (private val wikiMapDao: WikiMapDao, private val mapper: MapMapper): MapsRepository {
    override suspend fun getMapsAsync(): Deferred<List<GameMap>> = coroutineScope {
        async {
            wikiMapDao.deleteAll() //СУКА МАИН ТХРЕАД111
          //  wikiMapDao.insert(PREPOPULATE_DATA)
            val q = wikiMapDao.getAll().map { mapper.mapTo(it) }
            return@async q
            /*
            listOf(GameMap("BLIZZARD WORLD", R.drawable.blizzard_world),
                GameMap("NUMBANI", R.drawable.blizzard_world),
                GameMap("ROAD 66", R.drawable.blizzard_world),
                GameMap("LEIGHTZAN TOWER", R.drawable.blizzard_world),
                GameMap("HOLLYWOOD", R.drawable.blizzard_world),
                GameMap("ILIOS", R.drawable.blizzard_world))*/
        }
    }
}