package com.sc.overhub.model.statistics.heroes

data class HeroesAllModsPlayTime(
    val competitive: AllHeroesTimeStatistics,
    val quickplay: AllHeroesTimeStatistics
)