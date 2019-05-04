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

    var score: ObservableField<String> = ObservableField(repo.getCashedScore())
    var nickname: ObservableField<String> = ObservableField(repo.getBattleTag())
    var imageSrcId: ObservableInt = ObservableInt(R.drawable.diamond)

    init {
        launch {
            score.set(repo.getPlayerScore())
            // add choosing image id depends on score
        }
    }
}