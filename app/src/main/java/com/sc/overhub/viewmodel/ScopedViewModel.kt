package com.sc.overhub.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class ScopedViewModel : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = job
    private var job = Job()

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}