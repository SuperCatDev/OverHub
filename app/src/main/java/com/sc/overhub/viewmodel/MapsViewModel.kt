package com.sc.overhub.viewmodel

import com.sc.overhub.model.GameMap
import com.sc.overhub.repository.MapsRepository
import com.sc.overhub.view.adapter.MapsListAdapter
import kotlinx.coroutines.runBlocking
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class MapsViewModel : ScopedViewModel(), KoinComponent {
    private val repo: MapsRepository by inject()

    var adapter: MapsListAdapter =
        MapsListAdapter(getMapsFromRepo())

    private fun getMapsFromRepo(): List<GameMap> = runBlocking {
        repo.getMapsAsync().await()
    }
}