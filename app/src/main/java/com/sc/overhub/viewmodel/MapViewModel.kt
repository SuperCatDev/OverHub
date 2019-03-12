package com.sc.overhub.viewmodel

import androidx.databinding.ObservableField
import com.sc.overhub.repository.MapsRepository
import com.sc.overhub.view.adapter.MapImagesAdapter
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class MapViewModel(private val mapId: Int) : ScopedViewModel(), KoinComponent {
    private val repo: MapsRepository by inject()

    private val mapImagesIdAsync = async {
        repo.getMapImages(mapId)
    }

    private val mapDescriptionAsync = async {
        repo.getMapDescription(mapId)
    }

    val adapter: MapImagesAdapter =
        MapImagesAdapter(runBlocking { mapImagesIdAsync.await() })

    val title: ObservableField<String> by lazy {
        ObservableField<String>(runBlocking { mapDescriptionAsync.await().first })
    }

    val description: ObservableField<String> by lazy {
        ObservableField<String>(runBlocking { mapDescriptionAsync.await().second })
    }
}