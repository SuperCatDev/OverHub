package com.sc.overhub.domain.model.statistics

import com.sc.overhub.domain.model.statistics.achivments.Achievements
import com.sc.overhub.domain.model.statistics.heroes.HeroesStats
import com.sc.overhub.domain.model.statistics.stats.Stats

/** Usually only one field will be not null.
 * it's depends on the request (stats/heroes/achievements)
 *
 * Exception is blob request
 * **/
data class FullStatistic(
    val heroes: HeroesStats?,
    val stats: Stats?,
    val achievements: Achievements?
)