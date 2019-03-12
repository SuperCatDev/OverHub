package com.sc.overhub.data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sc.overhub.R

@Database(entities = [WikiMapEntry::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {

    abstract fun wikiMapsDao(): WikiMapDao

    companion object {
        val PREPOPULATE_DATA = listOf(
            WikiMapEntry(null, name = "Blizzard1", imageId = R.drawable.blizzard_world),
            WikiMapEntry(null, name = "Blizzard2", imageId = R.drawable.blizzard_world),
            WikiMapEntry(null, name = "Blizzard3", imageId = R.drawable.blizzard_world),
            WikiMapEntry(null, name = "Blizzard4", imageId = R.drawable.blizzard_world),
            WikiMapEntry(null, name = "Blizzard5", imageId = R.drawable.blizzard_world)
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
                        //TODO: ВСТАВЛЯЕМ ПУСТЫЕ ШАБЛОННЫЕ ДАННЫЕ
                        ioThread {
                            //getInstance(context).wikiMapsDao().insert(PREPOPULATE_DATA)
                        }
                    }
                }).build()
    }
}