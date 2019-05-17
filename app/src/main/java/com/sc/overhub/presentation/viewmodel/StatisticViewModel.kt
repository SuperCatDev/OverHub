package com.sc.overhub.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sc.overhub.R
import com.sc.overhub.data.repository.ProfileRepository
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class StatisticViewModel : ViewModel(), KoinComponent {
    private val repo: ProfileRepository by inject()

    var score = MutableLiveData<String>()
    var nickname = MutableLiveData<String>().apply { value = repo.getBattleTag() }
    var imageSrcId = MutableLiveData<Int>()

    init {
        val cashedScore = repo.getCashedScore()
        score.value = cashedScore
        imageSrcId.value = if (cashedScore.isNotEmpty()) getRankIdDependsOnScore(cashedScore) else R.drawable.gm

        initScore()
    }

    private fun initScore() = viewModelScope.launch {
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