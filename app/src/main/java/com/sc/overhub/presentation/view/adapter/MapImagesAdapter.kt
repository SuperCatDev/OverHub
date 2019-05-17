package com.sc.overhub.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.sc.overhub.BR
import com.sc.overhub.R

class MapImagesAdapter(private val imagesList: List<Int>) : RecyclerView.Adapter<MapImagesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_wiki_map, parent, false
            )
        )
    }

    override fun getItemCount(): Int = imagesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imagesList, position)
    }

    class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imagesList: List<Int>, position: Int) {
            binding.setVariable(BR.viewSourceId, imagesList[position])
            binding.executePendingBindings()
        }
    }
}