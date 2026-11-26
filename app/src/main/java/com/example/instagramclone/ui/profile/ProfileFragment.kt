package com.example.instagramclone.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagramclone.R
import com.example.instagramclone.data.FakeData
import com.example.instagramclone.databinding.FragmentProfileBinding
import com.example.instagramclone.ui.common.GridAdapter
import com.example.instagramclone.ui.common.compact

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val b = FragmentProfileBinding.bind(view)

        b.handle.text = FakeData.me
        b.avatar.setImageResource(FakeData.myAvatar)
        b.postCount.text = FakeData.POSTS
        b.followerCount.text = compact(FakeData.FOLLOWERS)
        b.followingCount.text = compact(FakeData.FOLLOWING)
        b.fullName.text = FakeData.FULL_NAME
        b.bio.text = FakeData.BIO
        b.link.text = FakeData.LINK

        b.highlights.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        b.highlights.adapter = HighlightAdapter()

        val gridAdapter = GridAdapter(FakeData.grid)
        b.grid.layoutManager = GridLayoutManager(requireContext(), 3)
        b.grid.adapter = gridAdapter

        val cells = listOf(b.tabGrid to b.indGrid, b.tabClips to b.indClips, b.tabTagged to b.indTagged)
        val sets = listOf(FakeData.grid, FakeData.covers, FakeData.explore.takeLast(9))
        cells.forEachIndexed { selected, (cell, _) ->
            cell.setOnClickListener {
                cells.forEachIndexed { i, (other, underline) ->
                    underline.visibility = if (i == selected) View.VISIBLE else View.INVISIBLE
                    (other.getChildAt(0) as ImageView).alpha = if (i == selected) 1f else 0.5f
                }
                gridAdapter.submit(sets[selected])
            }
        }
    }
}
