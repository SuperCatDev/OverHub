package com.sc.overhub.viewmodel

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import org.koin.standalone.KoinComponent

class StatisticViewModel : ScopedViewModel(), KoinComponent {
    var score: ObservableInt = ObservableInt(0)
    var nickname: ObservableField<String> = ObservableField("")
}