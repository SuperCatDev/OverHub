package com.sc.overhub.repository

import com.sc.overhub.api.ArcadeApi
import com.sc.overhub.data.ArcadeDao
import com.sc.overhub.data.arcade.ArcadeModeEntity
import com.sc.overhub.data.arcade.ArcadeTodayEntity
import com.sc.overhub.mapper.ArcadeMapper
import com.sc.overhub.model.ArcadeModel
import com.sc.overhub.model.ChangeTypeEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ArcadeRepository {
    suspend fun getArcadeListByCache(): List<ArcadeModel>
    suspend fun getLastUpdateByCache(): String?
    suspend fun getTodayArcadeByRemote(): ArcadeTodayEntity?
    suspend fun getLastUpdateByRemote(): String?
    suspend fun getArcadeModeByRemote(): List<ArcadeModeEntity>?
    suspend fun updateTodayArcadeCache(newTodayArcade: ArcadeTodayEntity)
    suspend fun updateArcadesModeCache(arcadesMode: List<ArcadeModeEntity>): List<Unit>
}

class ArcadeRepositoryImp(private val arcadeDao: ArcadeDao,
                          private val mapper: ArcadeMapper,
                          private val api: ArcadeApi): BaseApiRepository(), ArcadeRepository {

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

    override suspend fun getLastUpdateByCache(): String? = withContext(Dispatchers.IO) {
        arcadeDao.getLastUpdateTime()
    }

    override suspend fun getArcadeListByCache(): List<ArcadeModel>  = withContext(Dispatchers.IO) {
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

    override suspend fun getTodayArcadeByRemote(): ArcadeTodayEntity? = withContext(Dispatchers.IO) {
        val arcadeResponse = safeApiCall(
            call = {api.getTodayArcade().await()},
            errorMessage = "Error loading today arcade"
        )
        if (arcadeResponse != null) {
            return@withContext ArcadeTodayEntity(
                null,
                arcadeResponse.titleLarge.id,
                arcadeResponse.titleWeekly1.id,
                arcadeResponse.titleDaily.id,
                arcadeResponse.titleWeekly2.id,
                arcadeResponse.tilePermanent.id,
                arcadeResponse.updateAt
            )
        }
        return@withContext null
    }

    override suspend fun getLastUpdateByRemote(): String?  = withContext(Dispatchers.IO) {
        val arcadeResponse = safeApiCall(
            call = {api.getTodayArcade().await()},
            errorMessage = "Error loading today arcade"
        )
        return@withContext arcadeResponse?.updateAt
    }

    override suspend fun getArcadeModeByRemote(): List<ArcadeModeEntity>?  = withContext(Dispatchers.IO){
        val arcadeResponse = safeApiCall(
            call = {api.getArcadeModes().await()},
            errorMessage = "Error loading today arcade"
        )
        val arcadesMode: MutableList<ArcadeModeEntity> = mutableListOf()
        arcadeResponse?.map {
            arcadesMode.add(mapper.mapTo(it))
        }
        if (!arcadesMode.isEmpty()){
            return@withContext arcadesMode
        }
        return@withContext null
    }

    override suspend fun updateTodayArcadeCache(newTodayArcade: ArcadeTodayEntity) = withContext(Dispatchers.IO) {
        val oldTodayArcade = arcadeDao.getTodayArcade()
        if (oldTodayArcade != null) {
            newTodayArcade.id = oldTodayArcade.id
            arcadeDao.updateTodayArcade(newTodayArcade)
        } else {
            arcadeDao.I_insertTodayArcade(newTodayArcade)
        }
    }

    override suspend fun updateArcadesModeCache(arcadesMode: List<ArcadeModeEntity>) = withContext(Dispatchers.IO) {
        arcadesMode.map {
            if (arcadeDao.getModeArcadeById(it.id) != null) {
                arcadeDao.updateArcadeMode(it)
            } else {
                arcadeDao.insertArcadeMode(it)
            }
        }
    }
}