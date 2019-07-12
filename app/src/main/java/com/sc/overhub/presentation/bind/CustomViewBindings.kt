package com.sc.overhub.presentation.bind

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("android:src")
fun setImageDrawable(view: ImageView, resource: Int?) =
    Glide.with(view)
        .load(resource)
        .fitCenter()
        .into(view)
