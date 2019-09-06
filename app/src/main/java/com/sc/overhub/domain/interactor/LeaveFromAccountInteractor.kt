package com.sc.overhub.domain.interactor

import com.sc.overhub.data.repository.ProfileRepository

interface LeaveFromAccountInteractor {
    fun leave()
    fun subscribeOnLeave(callback: () -> Unit)
}

class LeaveFromAccountInteractorImpl(private val repo: ProfileRepository) : LeaveFromAccountInteractor {
    private var onLeaveCallback: (() -> Unit)? = null

    override fun subscribeOnLeave(callback: () -> Unit) {
        onLeaveCallback = callback
    }

    override fun leave() {
        repo.clearBattleTag()
        onLeaveCallback?.invoke()
    }
}