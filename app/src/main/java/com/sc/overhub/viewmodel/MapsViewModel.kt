package com.sc.overhub.viewmodel

import com.sc.overhub.model.GameMapForListModel
import com.sc.overhub.repository.MapsRepository
import com.sc.overhub.view.adapter.MapsListAdapter
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class MapsViewModel(var navigate: (Long) -> Unit) : ScopedViewModel(), KoinComponent {
    val size: Int by lazy { runBlocking { mapsAsync.await().size } }
    val maps: List<GameMapForListModel> by lazy { runBlocking { mapsAsync.await() } }

    private val repo: MapsRepository by inject()

    // This approach allow to start maps downloading before method invocation
    private val mapsAsync = async {
        repo.getMapsForList()
    }

    var adapter: MapsListAdapter =
        MapsListAdapter(this)
}
