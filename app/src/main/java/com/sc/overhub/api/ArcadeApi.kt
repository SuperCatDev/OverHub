package com.sc.overhub.api

import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

data class ArcadeResponse(
    @SerializedName("updated_at")
    val updateAt: String,

    @SerializedName("tile_large")
    val titleLarge: ArcadeBaseInfo,

    @SerializedName("tile_weekly_1")
    val titleWeekly1: ArcadeBaseInfo,

    @SerializedName("tile_daily")
    val titleDaily: ArcadeBaseInfo,

    @SerializedName("tile_weekly_2")
    val titleWeekly2: ArcadeBaseInfo,

    @SerializedName("tile_permanent")
    val tilePermanent: ArcadeBaseInfo,

    @SerializedName("by_user")
    val user: ArcadeUser
)

data class ArcadeUser(
    val id: Long,
    val battletag: String,
    val avatar: String
)

data class ArcadeBaseInfo(
    val id: Long,
    val name: String,
    val players: String,
    val code: String
)

interface ArcadeApi {
    @GET("today")
    fun getTodayArcade(): Deferred<Response<ArcadeResponse>>
}