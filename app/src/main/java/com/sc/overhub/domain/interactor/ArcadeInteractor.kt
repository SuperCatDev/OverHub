package com.sc.overhub.domain.interactor

import com.sc.overhub.data.repository.ArcadeRepository
import com.sc.overhub.domain.model.ArcadeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ArcadeInteractor {
    suspend fun getArcadeList(): List<ArcadeModel>
}

class ArcadeInteractorImpl(val repository: ArcadeRepository) : ArcadeInteractor {
    override suspend fun getArcadeList(): List<ArcadeModel> = withContext(Dispatchers.IO) {
        repository.getArcadeList()
    }
}