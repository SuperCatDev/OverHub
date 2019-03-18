package com.sc.overhub

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.sc.overhub.data.AppDataBase
import com.sc.overhub.data.WikiMapDao
import com.sc.overhub.data.wiki.map.WikiMapEntity
import com.sc.overhub.data.wiki.map.WikiMapImageEntity
import com.sc.overhub.data.wiki.map.WikiMapStatisticEntity
import com.sc.overhub.data.wiki.map.WikiMapTypeEntity
import com.sc.overhub.model.GameMapForListModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var dao: WikiMapDao
    private lateinit var db: AppDataBase

    private var typeMap = listOf<WikiMapTypeEntity>()
    private var images = listOf<WikiMapImageEntity>()
    private var stats = listOf<WikiMapStatisticEntity>()
    private var maps = listOf<WikiMapEntity>()

    @Before
    fun createDB() {
        db = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getContext(),
            AppDataBase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = db.wikiMapsDao()

        typeMap = listOf(
            WikiMapTypeEntity(246, "ESCORT"),
            WikiMapTypeEntity(5432, "ESCORT")
        )

        images = listOf(
            WikiMapImageEntity(null, 1, R.drawable.wiki_maps, true),
            WikiMapImageEntity(null, 2, R.drawable.wiki_maps, false),
            WikiMapImageEntity(null, 2, R.drawable.wiki_maps, true)
        )

        stats = listOf(
            WikiMapStatisticEntity(null, 1, "Example1"),
            WikiMapStatisticEntity(null, 2, "Example2"),
            WikiMapStatisticEntity(null, 1, "Example3")
        )
        maps = listOf(
            WikiMapEntity(1, "Map1", "Descrition1", 246 ),
            WikiMapEntity(2, "Map2", "Descrition2", 246 )
        )
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun onAddingTypeMap() = runBlocking {

        dao.insertTypeMap(typeMap)

        val result = dao.getTypeMap(246)
        assertEquals("ESCORT", result)
    }

    @Test
    @Throws(Exception::class)
    fun onAddingMapImage() = runBlocking {

        dao.insertMapImage(images)

        assertEquals(listOf(R.drawable.wiki_maps, R.drawable.wiki_maps), dao.getImagesById(2))
        assertEquals(R.drawable.wiki_maps, dao.getTitleImageById(2))
    }

    @Test
    @Throws(Exception::class)
    fun onAddingMapStatistic() = runBlocking {

        dao.insertStatistics(stats)
        assertEquals(listOf(stats[0].text, stats[2].text) ,dao.getStatisticsById(1))
    }

    @Test
    @Throws(Exception::class)
    fun onAddingMap() = runBlocking {

        dao.insertTypeMap(typeMap)
        dao.insertMapImage(images)
        dao.insertStatistics(stats)
        dao.insert(maps)

        assertEquals(maps[0], dao.getById(1))
        assertEquals(null, dao.getById(125))

        val req = GameMapForListModel(1, "Map1", R.drawable.wiki_maps, "ESCORT" )
        val req2 = GameMapForListModel(2, "Map2", R.drawable.wiki_maps, "ESCORT" )
        assertEquals(listOf(req, req2), dao.getMapsForList())
    }



}