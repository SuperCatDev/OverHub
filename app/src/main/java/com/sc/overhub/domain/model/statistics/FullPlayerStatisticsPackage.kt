package com.sc.overhub.domain.model.statistics

data class FullPlayerStatisticsPackage(
    val _request: Request?,
    val any: FullStatistic?,
    val eu: FullStatistic?,
    val kr: FullStatistic?,
    val us: FullStatistic?
)