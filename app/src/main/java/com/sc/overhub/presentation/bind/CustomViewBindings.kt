package com.sc.overhub.presentation.bind

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


@BindingAdapter("setAdapter")
fun bindRecyclerViewAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>?) {
    if (adapter == null) return

    recyclerView.setHasFixedSize(true)
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    recyclerView.adapter = adapter
}

@BindingAdapter("android:src")
fun setImageDrawable(view: ImageView, resource: Int?)  =
    Glide.with(view)
        .load(resource)
        .fitCenter()
        .into(view)
