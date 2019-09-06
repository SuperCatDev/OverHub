package com.sc.overhub.data.repository

import com.sc.overhub.data.db.AppDataBase
import com.sc.overhub.domain.mapper.ArcadeMapper
import com.sc.overhub.domain.mapper.HeroMapper
import com.sc.overhub.domain.mapper.MapMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.koin.core.KoinComponent

object DbRepositoryFactory : KoinComponent {
    lateinit var mapsReposImpl: MapsRepository
    lateinit var heroesReposImp: HeroesRepository
    lateinit var arcadeReposImp: ArcadeRepository

    suspend fun initAllRepo(database: AppDataBase) = coroutineScope {
        val asyncMap = getMapRepoAsync(database)
        val asyncHero = getHeroRepoAsync(database)
        val asyncArcade = getArcadeRepoAsync(database)

        mapsReposImpl = asyncMap.await()
        heroesReposImp = asyncHero.await()
        arcadeReposImp = asyncArcade.await()
    }

    private fun CoroutineScope.getMapRepoAsync(database: AppDataBase) = async {
        val mapsReposImpl =
            MapsRepositoryImpl(database.wikiMapsDao(), MapMapper())
        val data = mapsReposImpl.getMapsForList()
        if (data.isEmpty()) {
            mapsReposImpl.initDefault()
        }

        mapsReposImpl
    }

    private fun CoroutineScope.getHeroRepoAsync(database: AppDataBase) = async {
        val heroesReposImp =
            HeroesRepositoryImp(database.wikiHeroDao(), HeroMapper())
        val data = heroesReposImp.getHeroesForList()
        if (data.isEmpty()) {
            heroesReposImp.initDefault()
        }

        heroesReposImp
    }

    private fun CoroutineScope.getArcadeRepoAsync(database: AppDataBase) = async {
        val arcadeReposImp =
            ArcadeRepositoryImp(database.arcadeDao(), ArcadeMapper())
        val data = arcadeReposImp.getArcadeList()
        if (data.isEmpty()) {
            arcadeReposImp.initDefault()
        }

        arcadeReposImp
    }
}