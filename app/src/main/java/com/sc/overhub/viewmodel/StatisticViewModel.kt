package com.sc.overhub.viewmodel

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.sc.overhub.R
import com.sc.overhub.repository.ProfileRepository
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class StatisticViewModel : ScopedViewModel(), KoinComponent {
    private val repo: ProfileRepository by inject()

    var score: ObservableField<String>
    var nickname: ObservableField<String> = ObservableField(repo.getBattleTag())
    var imageSrcId: ObservableInt

    init {
        val cashedScore = repo.getCashedScore()
        score = ObservableField(cashedScore)
        imageSrcId = ObservableInt(if (cashedScore.isNotEmpty()) getRankIdDependsOnScore(cashedScore) else R.drawable.gm)

        launch {
            val scoreStr = repo.getPlayerScore()

            if (scoreStr.isEmpty())
                return@launch

            score.set(scoreStr)

            imageSrcId.set(getRankIdDependsOnScore(scoreStr))
        }
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