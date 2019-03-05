package com.sc.overhub.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.sc.overhub.BR
import com.sc.overhub.R
import com.sc.overhub.entry.WikiHeroOverviewEntry
import com.sc.overhub.viewmodel.WikiHeroViewModel

class WikiHeroOverviewAdapter(
    private val viewModel: WikiHeroViewModel): RecyclerView.Adapter<WikiHeroOverviewAdapter.ViewHolder>() {

    private var listDescription: List<WikiHeroOverviewEntry> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_wiki_hero_overview, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return listDescription.size
    }

    fun setListDescription( listDescription: List<WikiHeroOverviewEntry>){
        this.listDescription = listDescription
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(viewModel, position)
    }

    class ViewHolder(private val binding: ViewDataBinding):  RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: WikiHeroViewModel, position: Int){
            binding.setVariable(BR.viewModel, viewModel)
            binding.setVariable(BR.position, position)
            binding.executePendingBindings()
        }
    }
}