package com.sc.overhub.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sc.overhub.domain.interactor.LaunchInteractor
import kotlinx.coroutines.launch

class LaunchViewModel(private val interactor: LaunchInteractor) : ViewModel() {

    private val launchStatus = MutableLiveData<Boolean>()
    private val errorMessages = LiveEvent<String>()

    init {
        launchApp()
    }

    fun setBattleTag(battleTag: String) {
        viewModelScope.launch {
            val result = interactor.applyBattleTag(battleTag)

            if(result) {
                launchApp()
            } else {
                errorMessages.setValue("You Battle Tag is not valid")
                launchStatus.value = false
            }
        }
    }

    private fun launchApp() {
        viewModelScope.launch {
            val result = interactor.launchApp()

            launchStatus.value = result
        }
    }

    fun observeLaunchStatus(): LiveData<Boolean> = launchStatus
    fun observeErrorMessages(): LiveData<String> = errorMessages
}