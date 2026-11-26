package com.example.instagramclone.ui.common

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramclone.databinding.ItemGridPhotoBinding

class GridAdapter(private var items: List<Int>) : RecyclerView.Adapter<GridAdapter.Holder>() {

    class Holder(val b: ItemGridPhotoBinding) : RecyclerView.ViewHolder(b.root)

    @SuppressLint("NotifyDataSetChanged")
    fun submit(newItems: List<Int>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(ItemGridPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.b.photo.setImageResource(items[position])
    }

    override fun getItemCount() = items.size
}
