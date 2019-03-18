package com.sc.overhub.repository

import com.sc.overhub.data.WikiHeroDao
import com.sc.overhub.model.WikiHeroForList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface HeroesRepository {

    suspend fun getHeroesForList(): List<WikiHeroForList>

}

class HeroesRepositoryImp(private val wikiHeroDao: WikiHeroDao): HeroesRepository {

    suspend fun initDefault() = withContext(Dispatchers.IO) {
     //   wikiHeroDao.I_insertHero()
       // wikiHeroDao.I_insertHeroRole()
        //wikiHeroDao.I_insertSkill()
        //wikiHeroDao.I_insertSkillExtra()
        //wikiHeroDao.I_insertTip()
    }

    override suspend fun getHeroesForList(): List<WikiHeroForList> = withContext(Dispatchers.IO) {
        wikiHeroDao.getHeroesForList()
    }

}