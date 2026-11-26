package com.example.instagramclone.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramclone.R
import com.example.instagramclone.data.FakeData
import com.example.instagramclone.databinding.ItemStoryBinding

class StoryAdapter : RecyclerView.Adapter<StoryAdapter.Holder>() {

    class Holder(val b: ItemStoryBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = FakeData.stories.size + 1

    override fun onBindViewHolder(holder: Holder, position: Int) = with(holder.b) {
        if (position == 0) {
            avatar.setImageResource(FakeData.myAvatar)
            name.text = root.context.getString(R.string.your_story)
            ring.setBackgroundResource(R.drawable.bg_story_ring_seen)
            addBadge.visibility = View.VISIBLE
        } else {
            val story = FakeData.stories[position - 1]
            avatar.setImageResource(story.avatar)
            name.text = story.name
            ring.setBackgroundResource(
                if (story.seen) R.drawable.bg_story_ring_seen else R.drawable.bg_story_ring
            )
            addBadge.visibility = View.GONE
        }
    }
}
