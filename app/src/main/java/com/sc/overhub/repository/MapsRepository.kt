package com.sc.overhub.repository

import com.sc.overhub.R
import com.sc.overhub.model.GameMap
import com.sc.overhub.model.MapType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MapsRepository {
    // Suspend modifier guarantees that this methods
    // will be called from coroutine and was be controlled
    // by invoker coroutine scope
    suspend fun getMaps(): List<GameMap>

    suspend fun getIds(): List<Int>
    suspend fun getMapInfo(id: Int): GameMap
}

class MapsRepositoryImpl : MapsRepository {
    private val maps = mapOf(
        Pair(1, GameMap("Temple of Anubis", R.drawable.blizzard_world, MapType.ASSAULT)),
        Pair(2, GameMap("Hanamura", R.drawable.blizzard_world, MapType.ASSAULT)),
        Pair(3, GameMap("Volskaya Industries", R.drawable.blizzard_world, MapType.ASSAULT)),
        Pair(4, GameMap("Horizon Lunar Colony", R.drawable.blizzard_world, MapType.ASSAULT)),
        Pair(5, GameMap("Paris", R.drawable.blizzard_world, MapType.ASSAULT)),
        Pair(6, GameMap("Busan", R.drawable.blizzard_world, MapType.CONTROL)),
        Pair(7, GameMap("Ilios", R.drawable.blizzard_world, MapType.CONTROL)),
        Pair(8, GameMap("Lijiang Tower", R.drawable.blizzard_world, MapType.CONTROL)),
        Pair(9, GameMap("Nepal", R.drawable.blizzard_world, MapType.CONTROL)),
        Pair(10, GameMap("Oasis", R.drawable.blizzard_world, MapType.CONTROL)),
        Pair(11, GameMap("Dorado", R.drawable.blizzard_world, MapType.ESCORT)),
        Pair(12, GameMap("Junkertown", R.drawable.blizzard_world, MapType.ESCORT)),
        Pair(13, GameMap("Rialto", R.drawable.blizzard_world, MapType.ESCORT)),
        Pair(14, GameMap("Route 66", R.drawable.blizzard_world, MapType.ESCORT)),
        Pair(15, GameMap("Watchpoint: Gibraltar", R.drawable.blizzard_world, MapType.ESCORT)),
        Pair(16, GameMap("Blizzard World", R.drawable.blizzard_world, MapType.HYBRID)),
        Pair(17, GameMap("Hollywood", R.drawable.blizzard_world, MapType.HYBRID)),
        Pair(18, GameMap("King's Row", R.drawable.blizzard_world, MapType.HYBRID)),
        Pair(19, GameMap("Numbani", R.drawable.blizzard_world, MapType.HYBRID))
    )

    override suspend fun getMaps(): List<GameMap> = withContext(Dispatchers.IO) {
        maps.values.toList()
    }

    override suspend fun getIds(): List<Int> = withContext(Dispatchers.IO) {
        maps.keys.toList()
    }

    override suspend fun getMapInfo(id: Int): GameMap =
        maps[id] ?: GameMap("Temple of Anubis", R.drawable.blizzard_world, MapType.ASSAULT)
}
