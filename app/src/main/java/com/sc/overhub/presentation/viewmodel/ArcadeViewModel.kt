package com.sc.overhub.presentation.viewmodel

import android.view.View
import androidx.databinding.ObservableInt
import com.sc.overhub.domain.model.ArcadeModel
import com.sc.overhub.data.repository.ArcadeRepository
import com.sc.overhub.presentation.view.adapter.ArcadeAdapter
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class ArcadeViewModel : ScopedViewModel(), KoinComponent {
    private val repo: ArcadeRepository by inject()

    var loading: ObservableInt = ObservableInt(View.GONE)
    var showEmpty: ObservableInt = ObservableInt(View.GONE)

    private val arcades: List<ArcadeModel> by lazy { runBlocking { arcadeAsync.await() } }

    private val size: Int by lazy {  runBlocking { arcadeAsync.await().size } }

    private val arcadeAsync = async { repo.getArcadeList() }

    var arcadeAdapter: ArcadeAdapter =
        ArcadeAdapter(this)

    fun getArcadeByIndex(index: Int) = arcades[index]

    fun getArcadesSize() = size

    fun onClickReload() {
    }
}