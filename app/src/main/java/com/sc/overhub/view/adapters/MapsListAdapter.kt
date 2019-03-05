package com.sc.overhub.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR.mapName
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sc.overhub.R
import com.sc.overhub.model.GameMap

class MapsListAdapter(private val maps: List<GameMap>) : RecyclerView.Adapter<MapsListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_wiki_map, parent, false
            )
        )
    }

    override fun getItemCount(): Int = maps.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(maps, position)
    }

    class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(maps: List<GameMap>, position: Int) {
            Glide.with(binding.root)
                .load(maps[position].imageId)
                .fitCenter()
                .into(binding.root.findViewById(R.id.map_image))

            binding.root
            binding.setVariable(mapName, maps[position].name)
            binding.executePendingBindings()
        }
    }
}