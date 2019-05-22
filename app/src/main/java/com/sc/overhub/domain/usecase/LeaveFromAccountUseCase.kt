package com.sc.overhub.domain.usecase

import com.sc.overhub.data.repository.ProfileRepository
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

interface LeaveFromAccountUseCase {
    fun leave()

    fun subscribeOnLeave(callback: () -> Unit)
}

class LeaveFromAccountUseCaseImpl : LeaveFromAccountUseCase, KoinComponent {
    var onLeaveCallback: (() -> Unit)? = null

    override fun subscribeOnLeave(callback: () -> Unit) {
        onLeaveCallback = callback
    }

    private val repo: ProfileRepository by inject()

    override fun leave() {
        repo.clearBattleTag()
        onLeaveCallback?.invoke()
    }
}