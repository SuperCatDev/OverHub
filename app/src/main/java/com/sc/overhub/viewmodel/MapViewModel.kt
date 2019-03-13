package com.sc.overhub.viewmodel

import androidx.databinding.ObservableField
import com.sc.overhub.repository.MapsRepository
import com.sc.overhub.view.adapter.MapImagesAdapter
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class MapViewModel(private val mapId: Long) : ScopedViewModel(), KoinComponent {
    private val repo: MapsRepository by inject()

    private val mapImagesIdAsync = async {
        repo.getMapInfo(mapId).imagesId
    }

    private val mapDescriptionAsync = async {
        repo.getMapInfo(mapId).description
    }

    private val mapTitleAsync = async {
        repo.getMapInfo(mapId).name
    }

    val adapter: MapImagesAdapter =
        MapImagesAdapter(runBlocking { mapImagesIdAsync.await() })

    val title: ObservableField<String> by lazy {
        ObservableField<String>(runBlocking { mapTitleAsync.await() })
    }

    val description: ObservableField<String> by lazy {
        ObservableField<String>(runBlocking { mapDescriptionAsync.await() })
    }
}