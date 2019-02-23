package com.sc.overhub.view.fragment.wiki


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.sc.overhub.R
import com.sc.overhub.view.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_wiki.*

class WikiFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wiki, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragment_wiki_heroes_image.setOnClickListener {
            Navigation.findNavController(this.activity!!, R.id.nav_host_fragment)
                .navigate(R.id.action_wikiFragment_to_wikiHeroesListFragment)
        }
    }
}
