package com.sc.overhub.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class HomeActivityViewModel : BaseViewModel() {
    var currentTabId: ScreenId = ScreenId.STATISTIC
        private set

    private val visibleStatistic = MutableLiveData<Boolean>().apply { value = true }
    private val visibleArcade = MutableLiveData<Boolean>().apply { value = false }
    private val visibleWiki = MutableLiveData<Boolean>().apply { value = false }

    private val popEvent = LiveEvent<Unit>()

    fun observePopEvent(): LiveData<Unit> = popEvent

    fun observeVisibleStatistic(): LiveData<Boolean> = visibleStatistic
    fun observeVisibleArcade(): LiveData<Boolean> = visibleArcade
    fun observeVisibleWiki(): LiveData<Boolean> = visibleWiki

    fun handleTab(screenId: ScreenId) {
        if (currentTabId == screenId) {
            popEvent.setValue(Unit)
            return
        }

        when (screenId) {
            ScreenId.STATISTIC -> {
                visibleStatistic.value = true
                visibleArcade.value = false
                visibleWiki.value = false
            }
            ScreenId.ARCADE -> {
                visibleStatistic.value = false
                visibleArcade.value = true
                visibleWiki.value = false
            }
            ScreenId.WIKI -> {
                visibleStatistic.value = false
                visibleArcade.value = false
                visibleWiki.value = true
            }
        }

        currentTabId = screenId
    }

    enum class ScreenId {
        STATISTIC,
        ARCADE,
        WIKI
    }
}