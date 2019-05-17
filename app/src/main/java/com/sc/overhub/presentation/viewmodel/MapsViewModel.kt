package com.sc.overhub.presentation.viewmodel

import com.sc.overhub.domain.model.GameMapListModel
import com.sc.overhub.data.repository.MapsRepository
import com.sc.overhub.presentation.view.adapter.MapsListAdapter
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class MapsViewModel(var navigate: (Long) -> Unit) : ScopedViewModel(), KoinComponent {
    val size: Int by lazy { runBlocking { mapsAsync.await().size } }
    val maps: List<GameMapListModel> by lazy { runBlocking { mapsAsync.await() } }

    private val repo: MapsRepository by inject()

    // This approach allow to start maps downloading before method invocation
    private val mapsAsync = async {
        repo.getMapsForList()
    }

    var adapter: MapsListAdapter =
        MapsListAdapter(this)
}
