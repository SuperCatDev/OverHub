package com.sc.overhub.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sc.overhub.data.repository.MapsRepository
import com.sc.overhub.domain.model.GameMapListModel
import com.sc.overhub.presentation.view.adapter.MapsListAdapter
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MapsViewModel(var navigate: (Long) -> Unit) : ViewModel(), KoinComponent {
    private val repo: MapsRepository by inject()

    val size = MutableLiveData<Int>()
    val maps = MutableLiveData<List<GameMapListModel>>()
    val adapter = MutableLiveData<MapsListAdapter>()

    init {
        initValues()
    }

    private fun initValues() = viewModelScope.launch {
        val repoData = repo.getMapsForList()
        size.value = repoData.size
        maps.value = repoData
        adapter.value = MapsListAdapter(this@MapsViewModel)
    }
}
