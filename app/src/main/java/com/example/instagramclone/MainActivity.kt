package com.example.instagramclone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.instagramclone.databinding.ActivityMainBinding
import com.example.instagramclone.ui.explore.ExploreFragment
import com.example.instagramclone.ui.home.HomeFragment
import com.example.instagramclone.ui.messages.MessagesFragment
import com.example.instagramclone.ui.profile.ProfileFragment
import com.example.instagramclone.ui.reels.ReelsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var b: ActivityMainBinding
    private var current = -1

    private val tabs by lazy {
        with(b.bottomNav) { listOf(tabHome, tabReels, tabMessages, tabSearch, tabProfile) }
    }

    private val outlined = listOf(
        R.drawable.ic_home, R.drawable.ic_reels, R.drawable.ic_send,
        R.drawable.ic_search, R.drawable.ic_person
    )

    private val filled = listOf(
        R.drawable.ic_home_filled, R.drawable.ic_reels_filled, R.drawable.ic_send_filled,
        R.drawable.ic_search_filled, R.drawable.ic_person_filled
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        tabs.forEachIndexed { i, icon -> icon.setOnClickListener { select(i) } }
        select(savedInstanceState?.getInt(KEY_TAB) ?: 0)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_TAB, current)
    }

    private fun select(index: Int) {
        if (index == current) return
        val tx = supportFragmentManager.beginTransaction()
        supportFragmentManager.findFragmentByTag("tab$current")?.let { tx.hide(it) }
        val opened = supportFragmentManager.findFragmentByTag("tab$index")
        if (opened == null) tx.add(R.id.container, newScreen(index), "tab$index") else tx.show(opened)
        tx.commit()
        tabs.forEachIndexed { i, icon -> icon.setImageResource(if (i == index) filled[i] else outlined[i]) }
        current = index
    }

    private fun newScreen(index: Int): Fragment = when (index) {
        0 -> HomeFragment()
        1 -> ReelsFragment()
        2 -> MessagesFragment()
        3 -> ExploreFragment()
        else -> ProfileFragment()
    }

    private companion object {
        const val KEY_TAB = "tab"
    }
}
