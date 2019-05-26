package com.sc.overhub.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sc.overhub.data.repository.MapsRepository
import com.sc.overhub.presentation.view.adapter.MapImagesAdapter
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MapViewModel(private val mapId: Long) : ViewModel(), KoinComponent {
    private val repo: MapsRepository by inject()

    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val adapter = MutableLiveData<MapImagesAdapter>()

    init {
        initAdapter()
        initData()
    }

    private fun initAdapter() = viewModelScope.launch {
        adapter.value = MapImagesAdapter(repo.getMapInfo(mapId).imagesId)
    }

    private fun initData() = viewModelScope.launch {
        val info = repo.getMapInfo(mapId)
        title.value = info.name
        description.value = info.description
    }
}