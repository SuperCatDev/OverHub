package com.sc.overhub.domain.interactor

import android.text.TextUtils
import com.sc.overhub.data.db.AppDataBase
import com.sc.overhub.data.repository.DbRepositoryFactory
import com.sc.overhub.data.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface LaunchInteractor {
    suspend fun launchApp(): Boolean
    suspend fun applyBattleTag(battleTag: String): Boolean
}

class LaunchInteractorImpl(
    val repository: ProfileRepository,
    private val dataBase: AppDataBase
) : LaunchInteractor {
    override suspend fun applyBattleTag(battleTag: String): Boolean = withContext(Dispatchers.IO) {
        repository.setBattleTag(battleTag)
    }

    override suspend fun launchApp(): Boolean = withContext(Dispatchers.IO) {
        if (TextUtils.isEmpty(repository.getBattleTag())) {
            false
        } else {
            startApp()
            true
        }
    }

    private suspend fun startApp() {
        DbRepositoryFactory.initAllRepo(dataBase)
    }
}