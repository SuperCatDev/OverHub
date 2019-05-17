package com.sc.overhub.data.repository

import android.content.Context
import com.sc.overhub.data.db.AppDataBase
import com.sc.overhub.domain.mapper.ArcadeMapper
import com.sc.overhub.domain.mapper.HeroMapper
import com.sc.overhub.domain.mapper.MapMapper
import kotlinx.coroutines.runBlocking

object RepositoryFactory {
    lateinit var mapsReposImpl : MapsRepository

    lateinit var heroesReposImp: HeroesRepository

    lateinit var arcadeReposImp: ArcadeRepository

    fun getMapRepo(context: Context) = runBlocking {
        mapsReposImpl =  MapsRepositoryImpl(AppDataBase.getInstance(context).wikiMapsDao(), MapMapper())
        val data = mapsReposImpl.getMapsForList()
        if (data.isEmpty()) {
            (mapsReposImpl as MapsRepositoryImpl).initDefault()
        }
    }

    fun getHeroRepo(context: Context) = runBlocking {
        heroesReposImp = HeroesRepositoryImp(AppDataBase.getInstance(context).wikiHeroDao(), HeroMapper())
        val data = heroesReposImp.getHeroesForList()
        if (data.isEmpty()){
            (heroesReposImp as HeroesRepositoryImp).initDefault()
        }
    }

    fun getArcadeRepo(context: Context) = runBlocking {
        arcadeReposImp = ArcadeRepositoryImp(AppDataBase.getInstance(context).arcadeDao(), ArcadeMapper())
        val data = arcadeReposImp.getArcadeList()
        if (data.isEmpty()) {
            (arcadeReposImp as ArcadeRepositoryImp).initDefault()
        }
    }
}