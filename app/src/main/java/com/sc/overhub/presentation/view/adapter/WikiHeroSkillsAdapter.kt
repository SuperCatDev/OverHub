package com.sc.overhub.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.sc.overhub.BR
import com.sc.overhub.R
import com.sc.overhub.data.model.WikiHeroSkillModel
import com.sc.overhub.presentation.viewmodel.WikiHeroViewModel

interface WikiHeroSkillsHolder{
    fun bindViews(viewModel: WikiHeroViewModel, position: Int)
}

class WikiHeroSkillsAdapter(
    private val viewModel: WikiHeroViewModel): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_MAIN = 0
        const val TYPE_EXTRA  = 1
    }


    override fun getItemViewType(position: Int): Int = when (viewModel.getSkills()[position].type) {
        WikiHeroSkillModel.TYPE.MAIN -> TYPE_MAIN
        WikiHeroSkillModel.TYPE.EXTRA -> TYPE_EXTRA
        else -> TYPE_MAIN
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {
        TYPE_MAIN -> MainViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_wiki_hero_skills_main, parent, false))
        TYPE_EXTRA -> ExtraViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_wiki_hero_skills_extra, parent, false))
        else -> MainViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_wiki_hero_skills_main, parent, false))
    }


    override fun getItemCount(): Int {
        return viewModel.getSkills().size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as WikiHeroSkillsHolder).bindViews(viewModel, position)
    }

    class MainViewHolder(private val binding: ViewDataBinding):  RecyclerView.ViewHolder(binding.root), WikiHeroSkillsHolder {
        override fun bindViews(viewModel: WikiHeroViewModel, position: Int) {
            binding.setVariable(BR.viewModel, viewModel)
            binding.setVariable(BR.position, position)
            binding.executePendingBindings()
        }
    }

    class ExtraViewHolder(private val binding: ViewDataBinding):  RecyclerView.ViewHolder(binding.root), WikiHeroSkillsHolder {
        override fun bindViews(viewModel: WikiHeroViewModel, position: Int) {
            binding.setVariable(BR.viewModel, viewModel)
            binding.setVariable(BR.position, position)
            binding.executePendingBindings()
        }
    }
}