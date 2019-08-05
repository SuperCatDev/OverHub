package com.sc.overhub.presentation.viewmodel

import androidx.lifecycle.MutableLiveData

class PatchNoteViewModel : BaseViewModel() {
    val patchDescription = MutableLiveData<String>()
    val patchName = MutableLiveData<String>()
}