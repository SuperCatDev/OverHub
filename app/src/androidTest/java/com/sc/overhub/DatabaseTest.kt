package com.sc.overhub

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.sc.overhub.data.db.AppDataBase
import com.sc.overhub.data.db.WikiHeroDao
import com.sc.overhub.data.db.WikiMapDao
import com.sc.overhub.data.db.wiki.GameMapForList
import com.sc.overhub.data.db.wiki.WikiHero
import com.sc.overhub.data.db.wiki.WikiHeroForList
import com.sc.overhub.data.db.wiki.hero.WikiHeroEntity
import com.sc.overhub.data.db.wiki.hero.WikiHeroRoleEntity
import com.sc.overhub.data.db.wiki.hero.WikiHeroSkillEntity
import com.sc.overhub.data.db.wiki.hero.WikiHeroSkillExtraEntity
import com.sc.overhub.data.db.wiki.map.WikiMapEntity
import com.sc.overhub.data.db.wiki.map.WikiMapImageEntity
import com.sc.overhub.data.db.wiki.map.WikiMapStatisticEntity
import com.sc.overhub.data.db.wiki.map.WikiMapTypeEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class DatabaseTest {
    private lateinit var mapDao: WikiMapDao
    private lateinit var heroDao: WikiHeroDao
    private lateinit var db: AppDataBase

    private val typeMap = listOf(
        WikiMapTypeEntity(246, "ESCORT"),
        WikiMapTypeEntity(5432, "ESCORT")
    )
    private val images = listOf(
        WikiMapImageEntity(null, 1, R.drawable.wiki_maps, true),
        WikiMapImageEntity(null, 2, R.drawable.wiki_maps, false),
        WikiMapImageEntity(null, 2, R.drawable.wiki_maps, true)
    )
    private val stats = listOf(
        WikiMapStatisticEntity(null, 1, "Example1"),
        WikiMapStatisticEntity(null, 2, "Example2"),
        WikiMapStatisticEntity(null, 1, "Example3")
    )
    private val maps = listOf(
        WikiMapEntity(1, "Map1", "Descrition1", 246),
        WikiMapEntity(2, "Map2", "Descrition2", 246)
    )

    private val hero = listOf(
        WikiHeroEntity(11, "Таран", "", R.drawable.hero_portrait_hum, 3, 21),
        WikiHeroEntity(12, "Мерси", "", R.drawable.hero_portrait_hum, 1, 22)
    )
    private val heroRole = listOf(
        WikiHeroRoleEntity(21, "Танк"),
        WikiHeroRoleEntity(22, "Поддержка")
    )
    private val heroSkill = listOf(
        WikiHeroSkillEntity(31, 11, "Колесо", "Колесо", R.drawable.hero_portrait_hum),
        WikiHeroSkillEntity(32, 11, "Колесо2", "Колесо2", R.drawable.hero_portrait_hum),
        WikiHeroSkillEntity(33, 11, "Колесо3", "Колесо3", R.drawable.hero_portrait_hum),
        WikiHeroSkillEntity(41, 12, "Шифт", "Шифт", R.drawable.hero_portrait_hum)
    )
    private val heroSkillExtra = listOf(
        WikiHeroSkillExtraEntity(51, 31, "1", "2"),
        WikiHeroSkillExtraEntity(52, 31, "1", "2"),
        WikiHeroSkillExtraEntity(53, 31, "1", "2"),
        WikiHeroSkillExtraEntity(54, 41, "1", "2")
    )

    @Before
    fun createDB() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDataBase::class.java
        )
            .allowMainThreadQueries()
            .build()
        mapDao = db.wikiMapsDao()
        heroDao = db.wikiHeroDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun onAddingTypeMap() = runBlocking {

        mapDao.insertTypeMap(typeMap)

        val result = mapDao.getTypeMap(246)
        assertEquals("ESCORT", result)
    }

    @Test
    @Throws(Exception::class)
    fun onAddingMapImage() = runBlocking {

        mapDao.insertMapImage(images)

        assertEquals(listOf(R.drawable.wiki_maps, R.drawable.wiki_maps), mapDao.getImagesById(2))
        assertEquals(R.drawable.wiki_maps, mapDao.getTitleImageById(2))
    }

    @Test
    @Throws(Exception::class)
    fun onAddingMapStatistic() = runBlocking {

        mapDao.insertStatistics(stats)
        assertEquals(listOf(stats[0].text, stats[2].text), mapDao.getStatisticsById(1))
    }

    @Test
    @Throws(Exception::class)
    fun onAddingMap() = runBlocking {

        mapDao.insertTypeMap(typeMap)
        mapDao.insertMapImage(images)
        mapDao.insertStatistics(stats)
        mapDao.insert(maps)

        assertEquals(maps[0], mapDao.getById(1))
        assertEquals(null, mapDao.getById(125))

        val req = GameMapForList(1, "Map1", R.drawable.wiki_maps, "ESCORT")
        val req2 = GameMapForList(2, "Map2", R.drawable.wiki_maps, "ESCORT")
        assertEquals(listOf(req, req2), mapDao.getMapsForList())
    }


    @Test
    @Throws(Exception::class)
    fun onAddingHeroFromList() = runBlocking {
        heroDao.insertHeroRole(heroRole)
        heroDao.insertHero(hero)

        val result = listOf(
            WikiHeroForList(11, "Таран", "Танк", 3, R.drawable.hero_portrait_hum),
            WikiHeroForList(12, "Мерси", "Поддержка", 1, R.drawable.hero_portrait_hum)
        )

        assertEquals(result, heroDao.getHeroesForList())
    }

    @Test
    @Throws(Exception::class)
    fun onAddingHero() = runBlocking {
        heroDao.insertHero(hero)
        heroDao.insertHeroRole(heroRole)

        assertEquals(
            WikiHero(
                id = 11,
                name = "Таран",
                description = "",
                image = 2131230835,
                complexity = 3,
                role = "Танк"
            ), heroDao.getHeroById(hero[0].id!!)
        )
    }

    @Test
    @Throws(Exception::class)
    fun onAddingSkill() = runBlocking {
        heroDao.insertSkill(heroSkill)
        heroDao.insertSkillExtra(heroSkillExtra)

        val result = listOf(
            WikiHeroSkillEntity(31, 11, "Колесо", "Колесо", R.drawable.hero_portrait_hum),
            WikiHeroSkillEntity(32, 11, "Колесо2", "Колесо2", R.drawable.hero_portrait_hum),
            WikiHeroSkillEntity(33, 11, "Колесо3", "Колесо3", R.drawable.hero_portrait_hum)
        )
        assertEquals(result, heroDao.getHeroSkillsById(11))
    }
}