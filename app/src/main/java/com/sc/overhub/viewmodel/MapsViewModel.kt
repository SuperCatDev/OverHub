package com.sc.overhub.viewmodel

import com.sc.overhub.model.GameMap
import com.sc.overhub.repository.MapsRepository
import com.sc.overhub.view.adapter.MapsListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class MapsViewModel : ScopedViewModel(), KoinComponent {
    val size: Int by lazy { runBlocking { mapsAsync.await().size } }
    val maps: List<GameMap> by lazy { runBlocking { mapsAsync.await() } }

    private val repo: MapsRepository by inject()

    // This approach allow to start maps downloading before method invocation
    private val mapsAsync = async(Dispatchers.IO) {
        repo.getMaps()
    }

    private val idsAsync = async {
        repo.getIds()
    }

    fun getMapInfo(position: Int): GameMap = runBlocking {
        repo.getMapInfo(idsAsync.await()[position])
    }

    var adapter: MapsListAdapter =
        MapsListAdapter(this)
}
