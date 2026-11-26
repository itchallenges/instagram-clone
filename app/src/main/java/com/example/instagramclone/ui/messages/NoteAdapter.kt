package com.example.instagramclone.ui.messages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramclone.R
import com.example.instagramclone.data.FakeData
import com.example.instagramclone.databinding.ItemNoteBinding

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.Holder>() {

    class Holder(val b: ItemNoteBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = FakeData.notes.size + 1

    override fun onBindViewHolder(holder: Holder, position: Int) = with(holder.b) {
        val context = root.context
        if (position == 0) {
            avatar.setImageResource(FakeData.myAvatar)
            bubble.text = context.getString(R.string.leave_note)
            bubble.setTextColor(ContextCompat.getColor(context, R.color.ig_grey))
            name.text = context.getString(R.string.your_note)
        } else {
            val note = FakeData.notes[position - 1]
            avatar.setImageResource(note.avatar)
            bubble.text = note.text
            bubble.setTextColor(ContextCompat.getColor(context, R.color.ig_white))
            name.text = note.name
        }
    }
}
