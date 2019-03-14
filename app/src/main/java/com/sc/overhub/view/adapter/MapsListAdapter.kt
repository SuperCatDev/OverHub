package com.sc.overhub.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR.mapName
import androidx.recyclerview.widget.RecyclerView
import com.sc.overhub.BR.viewSourceId
import com.sc.overhub.R
import com.sc.overhub.model.GameMap
import com.sc.overhub.model.GameMapForListModel
import com.sc.overhub.viewmodel.MapsViewModel

class MapsListAdapter(private val viewModel: MapsViewModel) : RecyclerView.Adapter<MapsListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_wiki_map, parent, false
            )
        )
    }

    override fun getItemCount(): Int = viewModel.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(viewModel.maps, position, viewModel.navigate)
    }

    class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(maps: List<GameMapForListModel>, position: Int, navigate: (Int) -> Unit) {
            binding.setVariable(viewSourceId, maps[position].titleImageID)
            binding.root.setOnClickListener {
                //TODO: ПЕРЕДАВТЬ ID карты maps[position].id И LONG вместо INT

                navigate(position)
            }
            binding.setVariable(mapName, maps[position].name)
            binding.executePendingBindings()
        }
    }
}