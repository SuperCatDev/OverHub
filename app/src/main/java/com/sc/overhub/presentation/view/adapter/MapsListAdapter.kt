package com.sc.overhub.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR.mapName
import androidx.recyclerview.widget.RecyclerView
import com.sc.overhub.BR.viewSourceId
import com.sc.overhub.R
import com.sc.overhub.domain.model.GameMapListModel
import com.sc.overhub.presentation.viewmodel.MapsViewModel

class MapsListAdapter(private val viewModel: MapsViewModel) : RecyclerView.Adapter<MapsListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_wiki_map, parent, false
            )
        )
    }

    override fun getItemCount(): Int = viewModel.size.value!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(viewModel.maps.value!!, position, viewModel.navigate)
    }

    class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(maps: List<GameMapListModel>, position: Int, navigate: (Long) -> Unit) {
            binding.setVariable(viewSourceId, maps[position].titleImageID)
            binding.root.setOnClickListener {
                navigate(maps[position].id)
            }
            binding.setVariable(mapName, maps[position].name)
            binding.executePendingBindings()
        }
    }
}