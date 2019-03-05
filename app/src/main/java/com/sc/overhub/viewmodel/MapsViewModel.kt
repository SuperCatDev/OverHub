package com.sc.overhub.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.sc.overhub.view.adapters.MapsListAdapter

class MapsViewModel(var navigation: NavController) : ViewModel() {

    var adapter: MapsListAdapter = MapsListAdapter()
}