package com.sc.overhub.repository

import android.content.Context
import com.sc.overhub.data.AppDataBase
import com.sc.overhub.mapper.MapMapper
import kotlinx.coroutines.runBlocking

object RepositoryFactory {
    lateinit var mapsReposImpl : MapsRepository

    lateinit var heroesReposImp: HeroesRepository

    fun getMapRepo(context: Context) = runBlocking {
        mapsReposImpl =  MapsRepositoryImpl(AppDataBase.getInstance(context).wikiMapsDao(), MapMapper())
        val data = mapsReposImpl.getMapsForList()
        if(data.isEmpty()) {
            (mapsReposImpl as MapsRepositoryImpl).initDefault()
        }
    }

    fun getHeroRepo(context: Context) = runBlocking {
        heroesReposImp = HeroesRepositoryImp(AppDataBase.getInstance(context).wikiHeroDao())
        val data = heroesReposImp.getHeroesForList()
        if (data.isEmpty()){
            (heroesReposImp as HeroesRepositoryImp).initDefault()
        }
    }
}