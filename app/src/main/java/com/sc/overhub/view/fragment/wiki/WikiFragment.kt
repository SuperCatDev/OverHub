package com.sc.overhub.view.fragment.wiki

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.sc.overhub.R
import com.sc.overhub.view.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_wiki.*

class WikiFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_wiki, container, false)

        Glide.with(root)
            .load(R.drawable.wiki_heroes)
            .into(root.findViewById(R.id.fragment_wiki_heroes_image))

        Glide.with(root)
            .load(R.drawable.wiki_maps)
            .into(root.findViewById(R.id.fragment_wiki_maps_image))

        Glide.with(root)
            .load(R.drawable.wiki_updates)
            .into(root.findViewById(R.id.fragment_wiki_update_image))

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navigationController =
            Navigation.findNavController(activity!!, R.id.wiki_host_fragment)

        fragment_wiki_heroes_image.setOnClickListener {
            navigationController.navigate(R.id.action_wikiFragment_to_wikiHeroesListFragment)
        }

        fragment_wiki_maps_image.setOnClickListener {
            navigationController.navigate(R.id.action_wikiFragment_to_mapsFragment)
        }
    }
}
