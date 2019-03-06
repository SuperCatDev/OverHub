package com.sc.overhub.viewmodel

import android.view.View
import androidx.databinding.ObservableInt

class HomeActivityViewModel(var oldTabId: Int) : ScopedViewModel() {
    var visibleToolbar = ObservableInt(View.GONE)
    var visibleStatistic = ObservableInt(View.GONE)
    var visibleTracker = ObservableInt(View.GONE)
    var visibleWiki = ObservableInt(View.VISIBLE)
}