package com.sc.overhub.mapper

import com.sc.overhub.data.arcade.ArcadeModeEntity
import com.sc.overhub.model.ArcadeModel

class ArcadeMapper {
    fun mapTo(data: ArcadeModeEntity, title: String): ArcadeModel = when (title){
        ArcadeModel.TITLE.DAILY -> ArcadeModel.ArcadeModelDaily(data.name, data.playersMode, data.imageLarge, data.imageNormal)
        ArcadeModel.TITLE.WEEKLY -> ArcadeModel.ArcadeModelWeekly(data.name, data.playersMode, data.imageLarge, data.imageNormal)
        ArcadeModel.TITLE.LARGE -> ArcadeModel.ArcadeModelLarge(data.name, data.playersMode, data.imageLarge, data.imageNormal)
        ArcadeModel.TITLE.PERMANENT -> ArcadeModel.ArcadeModelPermanent(data.name, data.playersMode, data.imageLarge, data.imageNormal)
        else -> ArcadeModel.ArcadeModelPermanent(data.name, data.playersMode, data.imageLarge, data.imageNormal)
    }
}