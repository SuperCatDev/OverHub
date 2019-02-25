package com.sc.overhub.bind

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

    @BindingAdapter("setAdapter")
    fun bindRecyclerViewAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = adapter
    }