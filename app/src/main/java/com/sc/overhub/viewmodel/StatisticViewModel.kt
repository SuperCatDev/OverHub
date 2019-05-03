package com.sc.overhub.viewmodel

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.sc.overhub.repository.ProfileRepository
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class StatisticViewModel : ScopedViewModel(), KoinComponent {
    private val repo: ProfileRepository by inject()

    var score: ObservableInt = ObservableInt(0)
    var nickname: ObservableField<String> = ObservableField(repo.getBattleTag() ?: "")
}