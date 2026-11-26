package com.example.instagramclone.ui.explore

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.instagramclone.R
import com.example.instagramclone.data.FakeData
import com.example.instagramclone.databinding.FragmentExploreBinding

class ExploreFragment : Fragment(R.layout.fragment_explore) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val b = FragmentExploreBinding.bind(view)
        b.grid.layoutManager = GridLayoutManager(requireContext(), 3)
        b.grid.adapter = ExploreAdapter(FakeData.explore)
    }
}
