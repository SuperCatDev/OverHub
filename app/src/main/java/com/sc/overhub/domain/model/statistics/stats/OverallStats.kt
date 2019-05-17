package com.sc.overhub.domain.model.statistics.stats

data class OverallStats(
    val avatar: String,
    val comprank: Int,
    val endorsement_level: Int,
    val endorsement_shotcaller: Double,
    val endorsement_sportsmanship: Double,
    val endorsement_teammate: Double,
    val games: Any,
    val level: Int,
    val losses: Any,
    val prestige: Int,
    val rank_image: String,
    val tier: Any,
    val win_rate: Double,
    val wins: Int
)