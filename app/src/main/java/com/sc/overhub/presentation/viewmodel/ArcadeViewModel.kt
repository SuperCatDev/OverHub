package com.sc.overhub.presentation.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sc.overhub.data.repository.ArcadeRepository
import com.sc.overhub.domain.model.ArcadeModel
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.koin.core.KoinComponent
import org.koin.core.inject

class ArcadeViewModel : BaseViewModel() {
    private val repo: ArcadeRepository by inject()

    val loading = MutableLiveData<Int>().apply { value = View.GONE }
    val showEmpty = MutableLiveData<Int>().apply { value = View.GONE }

    private val arcades: List<ArcadeModel> by lazy { runBlocking { arcadeAsync.await() } }

    private val size: Int by lazy { runBlocking { arcadeAsync.await().size } }

    private val arcadeAsync = viewModelScope.async {
        repo.getArcadeList()
    }

    fun getArcadeByIndex(index: Int) = arcades[index]

    fun getArcadesSize() = size

    fun onClickReload() {
    }
}