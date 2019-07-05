package com.sc.overhub.data.repository

import com.sc.overhub.R
import com.sc.overhub.data.db.WikiHeroDao
import com.sc.overhub.data.db.wiki.hero.*
import com.sc.overhub.data.model.WikiHeroSkillModel
import com.sc.overhub.domain.mapper.HeroMapper
import com.sc.overhub.domain.model.WikiHeroListModel
import com.sc.overhub.domain.model.WikiHeroModel
import com.sc.overhub.domain.model.WikiHeroOverViewModel
import com.sc.overhub.domain.model.WikiHeroTipModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface HeroesRepository {

    suspend fun getHeroesForList(): List<WikiHeroListModel>
    suspend fun getHeroById(heroID: Long): WikiHeroModel
    suspend fun getHeroTips(heroId: Long): List<WikiHeroTipModel>
    suspend fun getHeroSkills(heroID: Long): List<WikiHeroSkillModel>
    suspend fun getHeroOverview(heroID: Long): List<WikiHeroOverViewModel>

}

class HeroesRepositoryImp(private val wikiHeroDao: WikiHeroDao, private val mapper: HeroMapper): HeroesRepository {

    suspend fun initDefault() = withContext(Dispatchers.IO) {
        val hero = listOf(
            WikiHeroEntity(11, "Таран", "", R.drawable.hero_portrait_hum, 3, 21),
            WikiHeroEntity(12, "Мерси", "", R.drawable.hero_portrait_hum, 1, 22)
        )

        val overview = listOf(
            WikiHeroOverviewEntity(500, 11, "121432", "4234234"),
            WikiHeroOverviewEntity(501, 11, "121432", "4234234"),
            WikiHeroOverviewEntity(502, 11, "121432", "4234234"))

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

        wikiHeroDao.insertHero(hero)
        wikiHeroDao.insertHeroRole(heroRole)
        wikiHeroDao.insertHeroOverview(overview)
        wikiHeroDao.insertSkill(heroSkill)
        wikiHeroDao.insertSkillExtra(heroSkillExtra)
        wikiHeroDao.insertTip(heroTip)
    }

    override suspend fun getHeroesForList(): List<WikiHeroListModel> = withContext(Dispatchers.IO) {
        wikiHeroDao.getHeroesForList().map { mapper.mapTo(it) }
    }

   override suspend fun getHeroById(heroID: Long): WikiHeroModel  = withContext(Dispatchers.IO) {
       mapper.mapTo(wikiHeroDao.getHeroById(heroID))
   }

    override suspend fun getHeroTips(heroId: Long): List<WikiHeroTipModel> = withContext(Dispatchers.IO) {
        wikiHeroDao.getHeroTipsById(heroId).map { mapper.mapTo(it) }
    }

    override suspend fun getHeroSkills(heroID: Long): List<WikiHeroSkillModel> = withContext(Dispatchers.IO) {
        var result = mutableListOf<WikiHeroSkillModel>()

        val skills = wikiHeroDao.getHeroSkillsById(heroID)
        for (skill in skills){
            result.add(mapper.mapTo(skill))
            val extra = wikiHeroDao.getHeroSkillsExtraById(skill.id!!)
            result.addAll(extra.map { mapper.mapTo(it) })
        }
        return@withContext result
    }

    override suspend fun getHeroOverview(heroID: Long): List<WikiHeroOverViewModel> = withContext(Dispatchers.IO) {
        wikiHeroDao.getHeroOverView(heroID).map { mapper.mapTo(it) }
    }
}