package com.sc.overhub.viewmodel

import android.view.View
import androidx.databinding.ObservableInt

class HomeActivityViewModel(var oldTabId: Int) : ScopedViewModel() {
    var visibleStatistic = ObservableInt(View.GONE)
    var visibleArcade = ObservableInt(View.GONE)
    var visibleWiki = ObservableInt(View.VISIBLE)
}