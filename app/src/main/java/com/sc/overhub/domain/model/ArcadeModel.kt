package com.sc.overhub.domain.model

data class ArcadeModel (
    val name: String,
    val playersMode: String,
    val imageLarge: String,
    val imageNormal: String,
    val changeType: ChangeTypeEnum)

enum class ChangeTypeEnum{
    DAILY, WEEKLY, LARGE, PERMANENT
}