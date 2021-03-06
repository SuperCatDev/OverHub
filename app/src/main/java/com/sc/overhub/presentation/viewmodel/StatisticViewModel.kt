package com.sc.overhub.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sc.overhub.R
import com.sc.overhub.data.repository.ProfileRepository
import com.sc.overhub.domain.usecase.LeaveFromAccountUseCase
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class StatisticViewModel : ViewModel(), KoinComponent {
    private val repo: ProfileRepository by inject()
    private val leaveUseCase: LeaveFromAccountUseCase by inject()

    var score = MutableLiveData<String>()
    var nickname = MutableLiveData<String>().apply { value = repo.getBattleTag() }
    var imageSrcId = MutableLiveData<Int>()
    var showDialog = false

    init {
        initScore()
    }

    fun leaveFromAccount(navigateToStartCallback: () -> Unit) {
        leaveUseCase.subscribeOnLeave(navigateToStartCallback)
        leaveUseCase.leave()
    }

    private fun initScore() = viewModelScope.launch {
        val cashedScore = repo.getCashedScore()
        score.value = cashedScore
        imageSrcId.value = if (cashedScore.isNotEmpty()) getRankIdDependsOnScore(cashedScore) else R.drawable.gm

        val scoreStr = repo.getPlayerScore()
        if (scoreStr.isEmpty())
            return@launch

        score.value = scoreStr

        imageSrcId.value = getRankIdDependsOnScore(scoreStr)
    }

    /** [score] must be int value */
    private fun getRankIdDependsOnScore(score: String) = when (score.toInt()) {
        in 0..1499 -> R.drawable.bronze
        in 1500..1999 -> R.drawable.silver
        in 2000..2499 -> R.drawable.gold
        in 2500..2999 -> R.drawable.platinum
        in 3000..3499 -> R.drawable.diamond
        in 3500..3999 -> R.drawable.master
        else -> R.drawable.gm
    }
}