package com.sc.overhub.view.fragment.wiki

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.sc.overhub.R
import com.sc.overhub.databinding.FragmentWikiBinding
import com.sc.overhub.view.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_wiki.*

class WikiFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentWikiBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_wiki, container, false
        )

        binding.heroesImageId = R.drawable.wiki_heroes
        binding.mapsImageId = R.drawable.wiki_maps
        binding.updatesImageId = R.drawable.wiki_updates

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragment_wiki_heroes_image.setOnClickListener {
            Navigation.findNavController(activity!!, R.id.wiki_host_fragment).navigate(R.id.action_wikiFragment_to_wikiHeroesListFragment)
        }

        fragment_wiki_maps_image.setOnClickListener {
            Navigation.findNavController(activity!!, R.id.wiki_host_fragment).navigate(R.id.action_wikiFragment_to_mapsFragment)
        }
    }
}
