package com.sc.overhub.viewmodel

import android.view.View
import androidx.databinding.ObservableInt

class HomeActivityViewModel : ScopedViewModel() {
    var currentTabId: Int = -1
    var visibleStatistic = ObservableInt(View.VISIBLE)
    var visibleArcade = ObservableInt(View.GONE)
    var visibleWiki = ObservableInt(View.GONE)
}