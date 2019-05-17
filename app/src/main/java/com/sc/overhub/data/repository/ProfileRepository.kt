package com.sc.overhub.data.repository

import android.content.Context
import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sc.overhub.domain.model.statistics.FullPlayerStatisticsPackage
import com.sc.overhub.domain.model.statistics.FullStatistic
import com.sc.overhub.domain.model.statistics.achivments.Achievements
import com.sc.overhub.domain.model.statistics.heroes.HeroesStats
import com.sc.overhub.domain.model.statistics.stats.Stats
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class ProfileRepository(context: Context) {
    private val sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    private val httpClient = StatisticHttpClient()

    fun getBattleTag(): String = sharedPreferences.getString(battleTagKey, "") ?: ""

    fun setBattleTag(battleTag: String): Boolean {
        if (!validateTag(battleTag)) return false

        sharedPreferences.edit().putString(battleTagKey, battleTag).apply()

        return true
    }

    fun getCashedScore(): String =
        sharedPreferences.getString(cashedScoreKey, "") ?: ""

    suspend fun getPlayerScore(): String = withContext(Dispatchers.IO) {
        val score: String =
            (httpClient.getPlayerStats(getBattleTag())?.competitive?.overall_stats?.comprank ?: "").toString()

        saveScoreToCash(score)

        score
    }

    suspend fun getHeroesStats(): HeroesStats? = httpClient.getHeroesStats(getBattleTag())
    suspend fun getAchievementsStats(): Achievements? = httpClient.getAchievementsStats(getBattleTag())

    // don't remove maybe it's be useful later
    suspend fun getAllStats(): FullStatistic? = httpClient.getAllStats(getBattleTag())

    private fun saveScoreToCash(score: String) {
        sharedPreferences.edit().putString(cashedScoreKey, score).apply()
    }

    companion object {
        const val prefName = "ProfileInfo"
        const val battleTagKey = "BattleTag"
        const val cashedScoreKey = "CashedScore"

        fun validateTag(battleTag: String): Boolean =
            battleTag.matches(Regex(".+#\\d+"))
    }
}

class StatisticHttpClient {
    private val client = Retrofit.Builder()
        .baseUrl("https://owapi.net/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(OverStatApi::class.java)

    suspend fun getPlayerStats(battleTag: String): Stats? {
        val packageData = getData(battleTag, RequestType.Player) ?: return null

        return (packageData.eu ?: packageData.kr ?: packageData.us ?: packageData.any)?.stats
    }

    suspend fun getHeroesStats(battleTag: String): HeroesStats? {
        val packageData = getData(battleTag, RequestType.Heroes) ?: return null

        return (packageData.eu ?: packageData.kr ?: packageData.us ?: packageData.any)?.heroes
    }

    suspend fun getAchievementsStats(battleTag: String): Achievements? {
        val packageData = getData(battleTag, RequestType.Achievements) ?: return null

        return (packageData.eu ?: packageData.kr ?: packageData.us ?: packageData.any)?.achievements
    }

    suspend fun getAllStats(battleTag: String): FullStatistic? {
        val packageData = getData(battleTag, RequestType.All) ?: return null

        return packageData.eu ?: packageData.kr ?: packageData.us ?: packageData.any
    }

    private suspend fun getData(battleTag: String, requestType: RequestType): FullPlayerStatisticsPackage? {
        val refactoredBattleTag = battleTag.replace("#", "-")

        var data: FullPlayerStatisticsPackage?

        var counter = 1L
        // try 3 time to download data
        while (true) {
            try {
                data = when (requestType) {
                    RequestType.Player -> client.getPlayerStatsAsync(refactoredBattleTag).await()
                    RequestType.Achievements -> client.getAchievementsAsync(refactoredBattleTag).await()
                    RequestType.Heroes -> client.getHeroesAsync(refactoredBattleTag).await()
                    RequestType.All -> client.getAllStatsAsync(refactoredBattleTag).await()
                }
                Log.i(TAG, "[getData] for: $refactoredBattleTag type: $requestType is : $data")

                if (data != null)
                    break

            } catch (th: Throwable) {
                Log.e(TAG, "[getData] Request failed for: $refactoredBattleTag type: $requestType : $th")
            }

            delay(1000 * counter)
            counter++
        }

        return data
    }

    companion object {
        const val TAG = "StatisticHttpClient"
    }
}

private enum class RequestType {
    Player,
    Achievements,
    Heroes,
    All
}

private interface OverStatApi {
    @GET("/api/v3/u/{battleTag}/stats")
    fun getPlayerStatsAsync(@Path("battleTag") battleTag: String): Deferred<FullPlayerStatisticsPackage?>

    @GET("/api/v3/u/{battleTag}/achievements")
    fun getAchievementsAsync(@Path("battleTag") battleTag: String): Deferred<FullPlayerStatisticsPackage?>

    @GET("/api/v3/u/{battleTag}/heroes")
    fun getHeroesAsync(@Path("battleTag") battleTag: String): Deferred<FullPlayerStatisticsPackage?>

    @GET("/api/v3/u/{battleTag}/blob")
    fun getAllStatsAsync(@Path("battleTag") battleTag: String): Deferred<FullPlayerStatisticsPackage?>
}
