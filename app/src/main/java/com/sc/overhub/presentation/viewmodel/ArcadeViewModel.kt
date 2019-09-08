package com.sc.overhub.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sc.overhub.domain.interactor.ArcadeInteractor
import com.sc.overhub.domain.model.ArcadeModel
import kotlinx.coroutines.launch

class ArcadeViewModel(interactor: ArcadeInteractor) : ViewModel() {

    private val arcades = MutableLiveData<List<ArcadeModel>>()
    private val showProgress = MutableLiveData<Boolean>().apply { value = true }

    init {
        viewModelScope.launch {
            arcades.value = interactor.getArcadeList()
            showProgress.value = false
        }
    }

    fun observeArcades(): LiveData<List<ArcadeModel>> = arcades
    fun observeShowProgress(): LiveData<Boolean> = showProgress
}