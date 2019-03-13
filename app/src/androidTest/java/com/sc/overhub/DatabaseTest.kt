package com.sc.overhub

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.sc.overhub.data.AppDataBase
import com.sc.overhub.data.WikiMapDao
import com.sc.overhub.data.wiki.map.WikiMapEntry
import com.sc.overhub.data.wiki.map.WikiMapImageEntry
import com.sc.overhub.data.wiki.map.WikiMapStatisticEntry
import com.sc.overhub.data.wiki.map.WikiMapTypeEntry
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

    @Before
    fun createDB(){
        db = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getContext(),
            AppDataBase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = db.wikiMapsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun onAddingTypeMap() = runBlocking {
        val typeMap = WikiMapTypeEntry(246, "ESCORT")
        val typeMap2 = WikiMapTypeEntry(5432, "ESCORT")

        dao.insertTypeMap(listOf(typeMap, typeMap2))

        val q = dao.getTypeMap(246)
        assertEquals("ESCORT", q)
    }

    @Test
    @Throws(Exception::class)
    fun onAddingMapImage() = runBlocking {
        val image = WikiMapImageEntry(null, 1, R.drawable.wiki_maps, true)
        val image2 = WikiMapImageEntry(null, 2, R.drawable.wiki_maps, false)
        val image3 = WikiMapImageEntry(null, 2, R.drawable.wiki_maps, true)
        dao.insertMapImage(listOf(image, image2, image3))

        assertEquals(listOf(R.drawable.wiki_maps, R.drawable.wiki_maps), dao.getImagesById(2))
        assertEquals(R.drawable.wiki_maps, dao.getTitleImageById(2))
    }

    @Test
    @Throws(Exception::class)
    fun onAddingMapStatistic() = runBlocking {
        val stat = WikiMapStatisticEntry(null, 1, "Example1")
        val stat2 = WikiMapStatisticEntry(null, 2, "Example2")
        val stat3 = WikiMapStatisticEntry(null, 1, "Example3")

        dao.insertStatistics(listOf(stat, stat2, stat3))

        assertEquals(listOf(stat.text, stat3.text) ,dao.getStatisticsById(1))
    }

    @Test
    @Throws(Exception::class)
    fun onAddingMap() = runBlocking {
        val typeMap: WikiMapTypeEntry = WikiMapTypeEntry(246, "ESCORT")
        dao.insertTypeMap(listOf(typeMap))

        val image = WikiMapImageEntry(null, 1, R.drawable.wiki_maps, true)
        val image2 = WikiMapImageEntry(null, 2, R.drawable.wiki_maps, false)
        val image3 = WikiMapImageEntry(null, 2, R.drawable.wiki_maps, true)
        dao.insertMapImage(listOf(image, image2, image3))

        val stat = WikiMapStatisticEntry(null, 1, "Example1")
        val stat2 = WikiMapStatisticEntry(null, 2, "Example2")
        val stat3 = WikiMapStatisticEntry(null, 1, "Example3")
        dao.insertStatistics(listOf(stat, stat2, stat3))

        val map = WikiMapEntry(1, "Map1", "Descrition1", 246 )
        val map2 = WikiMapEntry(2, "Map2", "Descrition2", 246 )
        dao.insert(listOf(map, map2))

        assertEquals(map, dao.getById(1))
        assertEquals(null, dao.getById(125))

        val req = GameMapForListModel(1, "Map1", R.drawable.wiki_maps, "ESCORT" )
        val req2 = GameMapForListModel(2, "Map2", R.drawable.wiki_maps, "ESCORT" )
        assertEquals(listOf(req, req2), dao.getMapsForList())
    }



}