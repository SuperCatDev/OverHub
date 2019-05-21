package com.sc.overhub.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sc.overhub.data.repository.MapsRepository
import com.sc.overhub.domain.model.GameMapListModel
import com.sc.overhub.presentation.view.adapter.MapsListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class MapsViewModel(var navigate: (Long) -> Unit) : ViewModel(), KoinComponent {
    private val repo: MapsRepository by inject()

    val size = MutableLiveData<Int>()
    val maps = MutableLiveData<List<GameMapListModel>>()
    val adapter = MutableLiveData<MapsListAdapter>()
/*
    val size: Int by lazy { runBlocking { mapsAsync.await().size } }
    val maps: List<GameMapListModel> by lazy { runBlocking { mapsAsync.await() } }
*/

    // This approach allow to start maps downloading before method invocation
    private val mapsAsync = viewModelScope.async(Dispatchers.Default) {
        repo.getMapsForList()
    }

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