package com.sc.overhub.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sc.overhub.R
import com.sc.overhub.data.wiki.map.WikiMapEntity
import com.sc.overhub.data.wiki.map.WikiMapImageEntity
import com.sc.overhub.data.wiki.map.WikiMapStatisticEntity
import com.sc.overhub.data.wiki.map.WikiMapTypeEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Database(
    entities = [
        WikiMapEntity::class,
        WikiMapImageEntity::class,
        WikiMapStatisticEntity::class,
        WikiMapTypeEntity::class],
    version = 1, exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun wikiMapsDao(): WikiMapDao

    companion object {
        val PREPOPULATE_DATA_TYPE = listOf(
            WikiMapTypeEntity(451, "escort"),
            WikiMapTypeEntity(452, "king of the hill")
        )

        val PREPOPULATE_DATA_IMAGE = listOf(
            WikiMapImageEntity(null, 543, R.drawable.wiki_maps, true),
            WikiMapImageEntity(null, 543, R.drawable.wiki_maps, false),
            WikiMapImageEntity(null, 550, R.drawable.wiki_maps, true),
            WikiMapImageEntity(null, 550, R.drawable.wiki_maps, false)
        )

        val PREPOPULATE_DATA_STATS = listOf(
            WikiMapStatisticEntity(null, 543, "STATS1"),
            WikiMapStatisticEntity(null, 543, "STATS2"),
            WikiMapStatisticEntity(null, 543, "STATS3"),
            WikiMapStatisticEntity(null, 550, "STATS1")
        )
        val PREPOPULATE_DATA = listOf(
            WikiMapEntity(
                543,
                "route 66",
                "Though the travelers and road trippers who used to cross the US on historic Route 66 are gone, the Main Street of America still stands, a testament to a simpler time. The gas stations, roadside shops, and cafes have gone into disuse, and the fabled Deadlock Gorge is mostly seen from the comfort of transcontinental train cars. But amid the fading monuments of that earlier era, the outlaws of the Deadlock Gang are planning their biggest heist yet",
                451
            ),
            WikiMapEntity(
                550,
                "Ilios",
                "Ilios is located in the Aegean sea of Greece, which is part of the Mediterranean Sea.",
                452
            )
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

                        GlobalScope.launch(IO) {
                            getInstance(context).wikiMapsDao().I_insertMapImage(PREPOPULATE_DATA_IMAGE)
                            getInstance(context).wikiMapsDao().I_insertTypeMap(PREPOPULATE_DATA_TYPE)
                            getInstance(context).wikiMapsDao().I_insertStatistics(PREPOPULATE_DATA_STATS)
                            getInstance(context).wikiMapsDao().I_insert(PREPOPULATE_DATA)
                        }
                    }
                }).build()
    }
}