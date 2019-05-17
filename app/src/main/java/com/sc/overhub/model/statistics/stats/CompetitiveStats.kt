package com.sc.overhub.model.statistics.stats

data class CompetitiveStats(
    val average_stats: AverageStats,
    val competitive: Boolean,
    val game_stats: GameStats,
    val overall_stats: OverallStats
)