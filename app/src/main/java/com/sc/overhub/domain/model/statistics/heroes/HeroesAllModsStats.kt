package com.sc.overhub.domain.model.statistics.heroes

data class HeroesAllModsStats(
    val competitive: AllHeroStatistic,
    val quickplay: AllHeroStatistic
)