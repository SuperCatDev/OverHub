package com.sc.overhub.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.sc.overhub.BR
import com.sc.overhub.R
import com.sc.overhub.entity.WikiHeroesListEntity
import com.sc.overhub.viewmodel.WikiHeroListViewModel


class WikHeroListAdapter(private val viewModel: WikiHeroListViewModel) : RecyclerView.Adapter<WikHeroListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_wiki_heroes_list, parent, false
            )
        )
    }

    override fun getItemCount(): Int = viewModel.getSize()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(viewModel, position)
    }

    class ViewHolder(private val binding: ViewDataBinding ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: WikiHeroListViewModel, position: Int){
            binding.setVariable(BR.viewModel, viewModel)
            binding.setVariable(BR.position, position)
            binding.executePendingBindings()
        }
    }
}