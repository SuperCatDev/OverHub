package com.sc.overhub.repository

import com.sc.overhub.R
import com.sc.overhub.model.GameMap
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

interface MapsRepository {
    suspend fun getMapsAsync(): Deferred<List<GameMap>>
}

class MapsRepositoryImpl : MapsRepository {
    override suspend fun getMapsAsync(): Deferred<List<GameMap>> = coroutineScope {
        async {
            listOf(GameMap("BLIZZARD WORLD", R.drawable.blizzard_world),
                GameMap("NUMBANI", R.drawable.blizzard_world),
                GameMap("ROAD 66", R.drawable.blizzard_world),
                GameMap("LEIGHTZAN TOWER", R.drawable.blizzard_world),
                GameMap("HOLLYWOOD", R.drawable.blizzard_world),
                GameMap("ILIOS", R.drawable.blizzard_world))
        }
    }
}