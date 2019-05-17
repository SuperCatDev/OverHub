package com.sc.overhub.domain.mapper

import com.sc.overhub.data.db.arcade.ArcadeModeEntity
import com.sc.overhub.domain.model.ArcadeModel
import com.sc.overhub.domain.model.ChangeTypeEnum

class ArcadeMapper {
    fun mapTo(data: ArcadeModeEntity, title: ChangeTypeEnum): ArcadeModel {
        return ArcadeModel(data.name, data.playersMode, data.imageLarge, data.imageNormal, title)
    }
}