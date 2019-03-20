package com.sc.overhub.repository

import com.sc.overhub.R
import com.sc.overhub.data.WikiHeroDao
import com.sc.overhub.data.wiki.hero.*
import com.sc.overhub.data.wiki.WikiHeroForList
import com.sc.overhub.mapper.HeroMapper
import com.sc.overhub.model.WikiHeroListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface HeroesRepository {

    suspend fun getHeroesForList(): List<WikiHeroListModel>

}

class HeroesRepositoryImp(private val wikiHeroDao: WikiHeroDao, private val mapper: HeroMapper): HeroesRepository {

    suspend fun initDefault() = withContext(Dispatchers.IO) {
        val hero = listOf(
            WikiHeroEntity(11, "Таран", "", R.drawable.hero_portrait_hum, 3, 21),
            WikiHeroEntity(12, "Мерси", "", R.drawable.hero_portrait_hum, 1, 22)
        )
        val heroRole = listOf(
            WikiHeroRoleEntity(21, "Танк"),
            WikiHeroRoleEntity(22, "Поддержка")
        )
        val heroSkill = listOf(
            WikiHeroSkillEntity(31, 11, "Колесо", "Колесо", R.drawable.hero_portrait_hum),
            WikiHeroSkillEntity(32, 11, "Колесо2", "Колесо2", R.drawable.hero_portrait_hum),
            WikiHeroSkillEntity(33, 11, "Колесо3", "Колесо3", R.drawable.hero_portrait_hum),
            WikiHeroSkillEntity(41, 12, "Шифт", "Шифт", R.drawable.hero_portrait_hum)
        )
        val heroSkillExtra = listOf(
            WikiHeroSkillExtraEntity(51, 31, "1", "2"),
            WikiHeroSkillExtraEntity(52, 31, "1", "2"),
            WikiHeroSkillExtraEntity(53, 31, "1", "2"),
            WikiHeroSkillExtraEntity(54, 41, "1", "2")
        )
        val heroTip = listOf(
            WikiHeroTipEntity(980, 11, "12414324123"),
            WikiHeroTipEntity(981, 11, "12414324123"),
            WikiHeroTipEntity(982, 11, "12414324123")
        )

        wikiHeroDao.I_insertHero(hero)
        wikiHeroDao.I_insertHeroRole(heroRole)
        wikiHeroDao.I_insertSkill(heroSkill)
        wikiHeroDao.I_insertSkillExtra(heroSkillExtra)
        wikiHeroDao.I_insertTip(heroTip)
    }

    override suspend fun getHeroesForList(): List<WikiHeroListModel> = withContext(Dispatchers.IO) {
        wikiHeroDao.getHeroesForList().map { mapper.mapTo(it) }
    }

}