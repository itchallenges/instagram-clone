package com.example.instagramclone.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramclone.R
import com.example.instagramclone.data.FakeData
import com.example.instagramclone.databinding.ItemHighlightBinding

class HighlightAdapter : RecyclerView.Adapter<HighlightAdapter.Holder>() {

    class Holder(val b: ItemHighlightBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(ItemHighlightBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = FakeData.highlights.size + 1

    override fun onBindViewHolder(holder: Holder, position: Int) = with(holder.b) {
        if (position == FakeData.highlights.size) {
            cover.setImageDrawable(null)
            plus.visibility = View.VISIBLE
            title.text = root.context.getString(R.string.new_highlight)
        } else {
            val highlight = FakeData.highlights[position]
            cover.setImageResource(highlight.cover)
            plus.visibility = View.GONE
            title.text = highlight.title
        }
    }
}
