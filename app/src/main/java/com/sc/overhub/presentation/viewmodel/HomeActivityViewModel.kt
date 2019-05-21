package com.sc.overhub.presentation.viewmodel

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeActivityViewModel : ViewModel() {
    var currentTabId: Int = -1
    val visibleStatistic = MutableLiveData<Int>().apply { value = View.VISIBLE }
    val visibleArcade = MutableLiveData<Int>().apply { value = View.GONE }
    val visibleWiki = MutableLiveData<Int>().apply { value = View.GONE }
}