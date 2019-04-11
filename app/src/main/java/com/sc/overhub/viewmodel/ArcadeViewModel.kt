package com.sc.overhub.viewmodel

import android.annotation.SuppressLint
import android.view.View
import androidx.databinding.ObservableInt
import com.sc.overhub.model.ArcadeModel
import com.sc.overhub.repository.ArcadeRepository
import com.sc.overhub.view.adapter.ArcadeAdapter
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.text.SimpleDateFormat

class ArcadeViewModel : ScopedViewModel(), KoinComponent {

    private val repo: ArcadeRepository by inject()

    var loading: ObservableInt = ObservableInt(View.GONE)
    var showEmpty: ObservableInt = ObservableInt(View.GONE)

    private val arcades: List<ArcadeModel> by lazy { runBlocking { arcadeAsync.await() } }

    private val size: Int by lazy {  runBlocking { arcadeAsync.await().size } }

    @SuppressLint("SimpleDateFormat")
    private val arcadeAsync = async {
        val cacheUpdate =  SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(repo.getLastUpdateByCache())
        val remoteUpdate =  SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(repo.getLastUpdateByRemote())
        if (remoteUpdate.after(cacheUpdate)){
            val newArcades = repo.getTodayArcadeByRemote()
            if (newArcades != null) {
                repo.getArcadeModeByRemote()?.let { repo.updateArcadesModeCache(it) }
                repo.updateTodayArcadeCache(newArcades)
            }
        }
        return@async repo.getArcadeListByCache()
    }

    var arcadeAdapter: ArcadeAdapter =
        ArcadeAdapter(this)

    fun getArcadeByIndex(index: Int) = arcades[index]

    fun getArcadesSize() = size

    fun onClickReload() {

    }


}