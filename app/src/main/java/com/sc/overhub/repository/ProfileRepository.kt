package com.sc.overhub.repository

import android.content.Context
import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class ProfileRepository(context: Context) {
    private val sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    private val httpClient = StatisticHttpClient()

    fun getBattleTag(): String? = sharedPreferences.getString(battleTagKey, "")

    fun setBattleTag(battleTag: String): Boolean {
        Log.e("ProfileRepository", "Setting a battle tag : $battleTag")
        if (!validateTag(battleTag)) return false

        sharedPreferences.edit().putString(battleTagKey, battleTag).apply()

        return true
    }

    suspend fun getMainStats() : String = httpClient.getStats(getBattleTag() ?: "")

    companion object {
        const val prefName = "ProfileSetup"
        const val battleTagKey = "BattleTag"

        fun validateTag(battleTag: String): Boolean =
            battleTag.matches(Regex(".+#\\d+"))
    }
}

class StatisticHttpClient() {
    private val client = Retrofit.Builder()
        .baseUrl("https://owapi.net/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(OverStatApi::class.java)

    suspend fun getStats(battleTag: String): String {
        val data = client.getStatsAsync(battleTag).await()
        Log.i("StatisticHttpClient", "Stats for: $battleTag is : $data")
        return data
    }

}

private interface OverStatApi {
    @GET("/api/v3/u/{battleTag}/stats")
    fun getStatsAsync(@Path("battleTag") battleTag: String): Deferred<String>
}