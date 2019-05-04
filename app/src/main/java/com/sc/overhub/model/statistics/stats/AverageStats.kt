package com.sc.overhub.model.statistics.stats

data class AverageStats(
    val damage_done_avg: Double,
    val deaths_avg: Double,
    val defensive_assists_avg: Double,
    val eliminations_avg: Double,
    val final_blows_avg: Double,
    val healing_done_avg: Double,
    val melee_final_blows_avg: Double,
    val objective_kills_avg: Double,
    val objective_time_avg: Double,
    val offensive_assists_avg: Double,
    val solo_kills_avg: Double,
    val time_spent_on_fire_avg: Double
)