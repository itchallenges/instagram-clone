package com.example.instagramclone.ui.messages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagramclone.R
import com.example.instagramclone.data.FakeData
import com.example.instagramclone.databinding.FragmentMessagesBinding

class MessagesFragment : Fragment(R.layout.fragment_messages) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val b = FragmentMessagesBinding.bind(view)
        b.handle.text = FakeData.me
        b.chats.layoutManager = LinearLayoutManager(requireContext())
        b.chats.adapter = ChatAdapter(FakeData.chats)
    }
}
