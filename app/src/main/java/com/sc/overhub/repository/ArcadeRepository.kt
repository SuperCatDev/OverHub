package com.sc.overhub.repository

import com.sc.overhub.data.ArcadeDao
import com.sc.overhub.data.arcade.ArcadeModeEntity
import com.sc.overhub.data.arcade.ArcadeTodayEntity
import com.sc.overhub.mapper.ArcadeMapper
import com.sc.overhub.model.ArcadeModel
import com.sc.overhub.model.ChangeTypeEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ArcadeRepository {
    suspend fun getArcadeList(): List<ArcadeModel>
    suspend fun getLastUpdate(): String?
}

class ArcadeRepositoryImp(private val arcadeDao: ArcadeDao, private val mapper: ArcadeMapper): ArcadeRepository {

    suspend fun initDefault() = withContext(Dispatchers.IO) {
        arcadeDao.I_insertModeArcade(
            listOf(
                ArcadeModeEntity(
                    6, "Competitive Elimination", "6v6", "6v6competitiveelimination",
                    "http://overwatcharcade.today/img/modes_large/6v6competitiveelimination.jpg",
                    "http://overwatcharcade.today/img/modes/6v6competitiveelimination.jpg"
                ),
                ArcadeModeEntity(
                    22, "Mystery Deathmatch", "8 Player FFA", "mysterydeathmatch",
                    "http://overwatcharcade.today/img/modes_large/6v6competitiveelimination.jpg",
                    "http://overwatcharcade.today/img/modes/6v6competitiveelimination.jpg"
                ),
                ArcadeModeEntity(
                    25, "No Limits", "6v6", "nolimits",
                    "http://overwatcharcade.today/img/modes_large/6v6competitiveelimination.jpg",
                    "http://overwatcharcade.today/img/modes/6v6competitiveelimination.jpg"
                ),
                ArcadeModeEntity(
                    29, "Team Deathmatch", "4v4", "teamdeathmatch",
                    "http://overwatcharcade.today/img/modes_large/6v6competitiveelimination.jpg",
                    "http://overwatcharcade.today/img/modes/6v6competitiveelimination.jpg"
                ),
                ArcadeModeEntity(
                    24, "Mystery Heroes", "6v6", "mysteryheroes",
                    "http://overwatcharcade.today/img/modes_large/6v6competitiveelimination.jpg",
                    "http://overwatcharcade.today/img/modes/6v6competitiveelimination.jpg"
                )
            )
        )
        arcadeDao.I_insertTodayArcade(ArcadeTodayEntity(null, 6, 22, 25, 29, 24, "2019-04-02 02:01:04"))
    }

    override suspend fun getLastUpdate(): String? = withContext(Dispatchers.IO) {
        arcadeDao.getLastUpdateTime()
    }

    override suspend fun getArcadeList(): List<ArcadeModel>  = withContext(Dispatchers.IO) {
        val todayArcade = arcadeDao.getTodayArcade()
        var listArcade: List<ArcadeModel> = listOf()
        if (todayArcade != null){
            val dailyArcade = arcadeDao.getModeArcadeById(todayArcade.dailyId)
            val weeklyArcade1 = arcadeDao.getModeArcadeById(todayArcade.weeklyId1)
            val weeklyArcade2 = arcadeDao.getModeArcadeById(todayArcade.weeklyId2)
            val permanentlyArcade = arcadeDao.getModeArcadeById(todayArcade.permanentId)
            val largeArcade = arcadeDao.getModeArcadeById(todayArcade.largeId)

            if (dailyArcade == null || weeklyArcade1 == null ||
                weeklyArcade2 == null || permanentlyArcade == null
                || largeArcade == null) {
                return@withContext listArcade
            }

            listArcade = listOf(
                mapper.mapTo(largeArcade, ChangeTypeEnum.LARGE),
                mapper.mapTo(weeklyArcade1, ChangeTypeEnum.WEEKLY),
                mapper.mapTo(weeklyArcade2, ChangeTypeEnum.WEEKLY),
                mapper.mapTo(dailyArcade, ChangeTypeEnum.DAILY),
                mapper.mapTo(permanentlyArcade, ChangeTypeEnum.PERMANENT)
            )
        }
        return@withContext listArcade
    }
}