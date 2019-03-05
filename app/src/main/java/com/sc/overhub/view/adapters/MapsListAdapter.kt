package com.sc.overhub.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sc.overhub.R
import kotlinx.android.synthetic.main.item_wiki_heroes_list.view.*

class MapsListAdapter : RecyclerView.Adapter<MapsListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_wiki_map,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = 5

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /*
        holder.title.text = presenter.getHeroes()[position].name
        holder.image.setImageResource(presenter.getHeroes()[position].logo)
        holder.color.setHexColor(presenter.getHeroes()[position].borderColor, presenter.getHeroes()[position].mainColor)
        holder.card.setOnClickListener { presenter.onClick() }*/

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = "title"
        val image = "image"
        val color = "color"
        val layout = itemView.maps_item_layout
    }
}