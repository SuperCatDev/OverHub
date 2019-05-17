package com.sc.overhub.domain.model.statistics.heroes

data class HeroesAllModsPlayTime(
    val competitive: AllHeroesTimeStatistics,
    val quickplay: AllHeroesTimeStatistics
)