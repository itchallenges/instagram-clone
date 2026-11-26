package com.example.instagramclone.ui.messages

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramclone.R
import com.example.instagramclone.databinding.ItemChatBinding
import com.example.instagramclone.databinding.ItemNotesRowBinding
import com.example.instagramclone.model.Chat

class ChatAdapter(private val chats: List<Chat>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = chats.size + 1

    override fun getItemViewType(position: Int) = if (position == 0) TYPE_NOTES else TYPE_CHAT

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_NOTES) {
            NotesHolder(ItemNotesRowBinding.inflate(inflater, parent, false))
        } else {
            ChatHolder(ItemChatBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ChatHolder) holder.bind(chats[position - 1])
    }

    class NotesHolder(b: ItemNotesRowBinding) : RecyclerView.ViewHolder(b.root) {
        init {
            b.notes.layoutManager =
                LinearLayoutManager(b.root.context, LinearLayoutManager.HORIZONTAL, false)
            b.notes.adapter = NoteAdapter()
        }
    }

    class ChatHolder(private val b: ItemChatBinding) : RecyclerView.ViewHolder(b.root) {

        fun bind(chat: Chat) = with(b) {
            val context = root.context
            avatar.setImageResource(chat.avatar)
            name.text = chat.name
            preview.text = chat.preview
            time.text = context.getString(R.string.chat_time, chat.time)

            val weight = if (chat.unread) Typeface.BOLD else Typeface.NORMAL
            val line = if (chat.unread) R.color.ig_white else R.color.ig_grey
            name.setTypeface(null, weight)
            preview.setTypeface(null, weight)
            preview.setTextColor(ContextCompat.getColor(context, line))
            time.setTextColor(ContextCompat.getColor(context, line))
            dot.visibility = if (chat.unread) View.VISIBLE else View.GONE
        }
    }

    private companion object {
        const val TYPE_NOTES = 0
        const val TYPE_CHAT = 1
    }
}
