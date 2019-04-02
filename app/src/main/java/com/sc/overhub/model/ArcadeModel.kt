package com.sc.overhub.model

abstract class ArcadeModel (
    val title: String) {

    class TITLE {
        companion object {
            const val LARGE = "large"
            const val WEEKLY = "weekly"
            const val DAILY = "daily"
            const val PERMANENT = "permanent"
        }
    }

    data class ArcadeModelLarge(
        val name: String,
        val playersMode: String,
        val imageLarge: String,
        val imageNormal: String
    ): ArcadeModel(title = TITLE.LARGE)

    data class ArcadeModelWeekly(
        val name: String,
        val playersMode: String,
        val imageLarge: String,
        val imageNormal: String
    ): ArcadeModel(title = TITLE.WEEKLY)

    data class ArcadeModelDaily(
        val name: String,
        val playersMode: String,
        val imageLarge: String,
        val imageNormal: String
    ): ArcadeModel(title = TITLE.DAILY)

    data class ArcadeModelPermanent(
        val name: String,
        val playersMode: String,
        val imageLarge: String,
        val imageNormal: String
    ): ArcadeModel(title = TITLE.PERMANENT)



}