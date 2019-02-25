package com.sc.overhub.view.fragment.wiki.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.sc.overhub.R
import com.sc.overhub.view.adapters.MapsListAdapter
import com.sc.overhub.view.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_wiki_maps.*

class MapsFragment : BaseFragment() {
    private lateinit var adapter: MapsListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wiki_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MapsListAdapter(
            Navigation.findNavController(
                activity!!,
                R.id.nav_host_fragment
            )
        )

        fragment_maps_list.adapter = adapter
        fragment_maps_list.layoutManager = LinearLayoutManager(view.context)
    }
}