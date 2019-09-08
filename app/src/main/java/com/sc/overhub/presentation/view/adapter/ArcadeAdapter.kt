package com.sc.overhub.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sc.overhub.BR
import com.sc.overhub.R
import com.sc.overhub.domain.model.ArcadeModel

val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArcadeModel>() {
    override fun areItemsTheSame(oldItem: ArcadeModel, newItem: ArcadeModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ArcadeModel, newItem: ArcadeModel): Boolean {
        return true
    }
}

class ArcadeAdapter : ListAdapter<ArcadeModel, ArcadeAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_arcade, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.setVariable(BR.data, getItem(position))
            binding.executePendingBindings()
        }
    }
}