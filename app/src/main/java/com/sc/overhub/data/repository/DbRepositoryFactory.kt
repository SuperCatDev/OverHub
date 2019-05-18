package com.sc.overhub.data.repository

import android.content.Context
import com.sc.overhub.data.db.AppDataBase
import com.sc.overhub.domain.mapper.ArcadeMapper
import com.sc.overhub.domain.mapper.HeroMapper
import com.sc.overhub.domain.mapper.MapMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

object DbRepositoryFactory {
    lateinit var mapsReposImpl: MapsRepository

    lateinit var heroesReposImp: HeroesRepository

    lateinit var arcadeReposImp: ArcadeRepository

    suspend fun initAllRepo(context: Context) = coroutineScope {
        val asyncMap = getMapRepoAsync(context)
        val asyncHero = getHeroRepoAsync(context)
        val asyncArcade = getArcadeRepoAsync(context)

        mapsReposImpl = asyncMap.await()
        heroesReposImp = asyncHero.await()
        arcadeReposImp = asyncArcade.await()
    }

    private fun CoroutineScope.getMapRepoAsync(context: Context) = async(Dispatchers.IO) {
        val mapsReposImpl = MapsRepositoryImpl(AppDataBase.getInstance(context).wikiMapsDao(), MapMapper())
        val data = mapsReposImpl.getMapsForList()
        if (data.isEmpty()) {
            mapsReposImpl.initDefault()
        }

        mapsReposImpl
    }

    private fun CoroutineScope.getHeroRepoAsync(context: Context) = async(Dispatchers.IO) {
        val heroesReposImp = HeroesRepositoryImp(AppDataBase.getInstance(context).wikiHeroDao(), HeroMapper())
        val data = heroesReposImp.getHeroesForList()
        if (data.isEmpty()) {
            heroesReposImp.initDefault()
        }

        heroesReposImp
    }

    private fun CoroutineScope.getArcadeRepoAsync(context: Context) = async(Dispatchers.IO) {
        val arcadeReposImp = ArcadeRepositoryImp(AppDataBase.getInstance(context).arcadeDao(), ArcadeMapper())
        val data = arcadeReposImp.getArcadeList()
        if (data.isEmpty()) {
            arcadeReposImp.initDefault()
        }

        arcadeReposImp
    }
}