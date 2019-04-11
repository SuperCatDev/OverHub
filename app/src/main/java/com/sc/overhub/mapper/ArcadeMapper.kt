package com.sc.overhub.mapper

import com.sc.overhub.api.ArcadeModeResponse
import com.sc.overhub.data.arcade.ArcadeModeEntity
import com.sc.overhub.model.ArcadeModel
import com.sc.overhub.model.ChangeTypeEnum

class ArcadeMapper {
    fun mapTo(data: ArcadeModeEntity, title: ChangeTypeEnum): ArcadeModel {
        return ArcadeModel(data.name, data.playersMode, data.imageLarge, data.imageNormal, title)
    }

    fun mapTo(data: ArcadeModeResponse): ArcadeModeEntity{
        return ArcadeModeEntity(data.id, data.name, data.players, data.code, data.image.large, data.image.normal)
    }
}