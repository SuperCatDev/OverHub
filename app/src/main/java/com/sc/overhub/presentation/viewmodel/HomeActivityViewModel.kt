package com.sc.overhub.presentation.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeActivityViewModel : BaseViewModel() {
    var currentTabId: Int = -1
    val visibleStatistic = MutableLiveData<Int>().apply { value = View.VISIBLE }
    val visibleArcade = MutableLiveData<Int>().apply { value = View.GONE }
    val visibleWiki = MutableLiveData<Int>().apply { value = View.GONE }
}