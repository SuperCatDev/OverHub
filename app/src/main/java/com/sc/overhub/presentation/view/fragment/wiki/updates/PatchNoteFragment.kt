package com.sc.overhub.presentation.view.fragment.wiki.updates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sc.overhub.presentation.view.fragment.BaseFragment
import com.sc.overhub.presentation.viewmodel.PatchNoteViewModel
import com.sc.overhub.presentation.viewmodel.getViewModel

class PatchNoteFragment : BaseFragment() {
    private val viewModel: PatchNoteViewModel by lazy {
        getViewModel {
            PatchNoteViewModel()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

    }
}