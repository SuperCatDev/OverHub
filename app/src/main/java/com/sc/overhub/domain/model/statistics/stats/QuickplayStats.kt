package com.sc.overhub.domain.model.statistics.stats

data class QuickplayStats(
    val average_stats: AverageStats,
    val competitive: Boolean,
    val game_stats: GameStats,
    val overall_stats: OverallStats
)