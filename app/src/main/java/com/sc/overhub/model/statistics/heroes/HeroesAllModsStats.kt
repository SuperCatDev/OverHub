package com.sc.overhub.model.statistics.heroes

data class HeroesAllModsStats(
    val competitive: AllHeroStatistic,
    val quickplay: AllHeroStatistic
)