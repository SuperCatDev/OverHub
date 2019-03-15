package com.sc.overhub.repository

import android.content.Context
import com.sc.overhub.data.AppDataBase
import com.sc.overhub.mapper.MapMapper
import kotlinx.coroutines.runBlocking

object RepositoryFactory {
    lateinit var reposImpl : MapsRepository

    fun getMapRepo(context: Context) = runBlocking {
        reposImpl =  MapsRepositoryImpl(AppDataBase.getInstance(context).wikiMapsDao(), MapMapper())
        val data = reposImpl.getMapsForList()
        if(data.isEmpty()) {
            (reposImpl as MapsRepositoryImpl).initDefault()
        }
    }
}