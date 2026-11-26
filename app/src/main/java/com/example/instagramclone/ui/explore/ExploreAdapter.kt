package com.example.instagramclone.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramclone.databinding.ItemExplorePhotoBinding

class ExploreAdapter(private val items: List<Int>) : RecyclerView.Adapter<ExploreAdapter.Holder>() {

    class Holder(val b: ItemExplorePhotoBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(ItemExplorePhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.b.photo.setImageResource(items[position])
    }

    override fun getItemCount() = items.size
}
