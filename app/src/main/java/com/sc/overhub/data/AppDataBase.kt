package com.sc.overhub.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sc.overhub.R
import com.sc.overhub.data.wiki.map.WikiMapEntry
import com.sc.overhub.data.wiki.map.WikiMapImageEntry
import com.sc.overhub.data.wiki.map.WikiMapStatisticEntry
import com.sc.overhub.data.wiki.map.WikiMapTypeEntry


@Database(entities = [
    WikiMapEntry::class,
    WikiMapImageEntry::class,
    WikiMapStatisticEntry::class,
    WikiMapTypeEntry::class],
    version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {

    abstract fun wikiMapsDao(): WikiMapDao

    companion object {


        val PREPOPULATE_DATA_TYPE = listOf(
            WikiMapTypeEntry(451, "escort"),
            WikiMapTypeEntry(452, "king of the hill")
        )

        val PREPOPULATE_DATA_IMAGE = listOf(
            WikiMapImageEntry(null, 543, R.drawable.wiki_maps, true),
            WikiMapImageEntry(null, 543, R.drawable.wiki_maps, false),
            WikiMapImageEntry(null, 550, R.drawable.wiki_maps, true),
            WikiMapImageEntry(null, 550, R.drawable.wiki_maps, false)
        )

        val PREPOPULATE_DATA_STATS = listOf(
            WikiMapStatisticEntry(null, 543, "STATS1"),
            WikiMapStatisticEntry(null, 543, "STATS2"),
            WikiMapStatisticEntry(null, 543, "STATS3"),
            WikiMapStatisticEntry(null, 550, "STATS1")
        )
        val PREPOPULATE_DATA = listOf(
            WikiMapEntry(543, "route 66", "Though the travelers and road trippers who used to cross the US on historic Route 66 are gone, the Main Street of America still stands, a testament to a simpler time. The gas stations, roadside shops, and cafes have gone into disuse, and the fabled Deadlock Gorge is mostly seen from the comfort of transcontinental train cars. But amid the fading monuments of that earlier era, the outlaws of the Deadlock Gang are planning their biggest heist yet",
                451),
            WikiMapEntry(550, "Ilios", "Ilios is located in the Aegean sea of Greece, which is part of the Mediterranean Sea.", 452)
        )


        private var INSTANCE: AppDataBase? = null

        @Synchronized
        fun getInstance(context: Context): AppDataBase =
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java, "test_db_over.db"
            )
                .addCallback(object : Callback() {

                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        ioThread {
                            getInstance(context).wikiMapsDao().I_insertMapImage(PREPOPULATE_DATA_IMAGE)
                            getInstance(context).wikiMapsDao().I_insertTypeMap(PREPOPULATE_DATA_TYPE)
                            getInstance(context).wikiMapsDao().I_insertStatistics(PREPOPULATE_DATA_STATS)
                            getInstance(context).wikiMapsDao().I_insert(PREPOPULATE_DATA)
                        }
                    }

                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        ioThread {
                        }
                    }
                }).build()
    }
}