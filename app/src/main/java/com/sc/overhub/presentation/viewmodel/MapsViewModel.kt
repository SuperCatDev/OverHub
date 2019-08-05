package com.sc.overhub.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sc.overhub.data.repository.MapsRepository
import com.sc.overhub.domain.model.GameMapListModel
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MapsViewModel : BaseViewModel() {
    private val repo: MapsRepository by inject()
    var size: Int = 0
    var maps: List<GameMapListModel> = emptyList()

    val initAdapter = MutableLiveData<Unit>()
    val navigateToMap = LiveEvent<Long>()

    init {
        initValues()
    }

    fun mapPressed(mapId: Long) {
        navigateToMap.postValue(mapId)
    }

    private fun initValues() = viewModelScope.launch {
        val repoData = repo.getMapsForList()
        size = repoData.size
        maps = repoData

        initAdapter.value = Unit
    }
}
