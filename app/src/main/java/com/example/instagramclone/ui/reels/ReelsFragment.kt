package com.example.instagramclone.ui.reels

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.instagramclone.R
import com.example.instagramclone.data.FakeData
import com.example.instagramclone.databinding.FragmentReelsBinding

class ReelsFragment : Fragment(R.layout.fragment_reels) {

    private var player: ExoPlayer? = null
    private var pager: ViewPager2? = null
    private var showing: PlayerView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val b = FragmentReelsBinding.bind(view)
        pager = b.pager
        b.pager.orientation = ViewPager2.ORIENTATION_VERTICAL
        b.pager.adapter = ReelsAdapter(FakeData.reels)
        b.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) = playPage(position)
        })
    }

    override fun onStart() {
        super.onStart()
        player = ExoPlayer.Builder(requireContext()).build().apply {
            repeatMode = Player.REPEAT_MODE_ONE
            volume = 0f
        }
        pager?.let { p -> p.post { playPage(p.currentItem) } }
    }

    override fun onStop() {
        super.onStop()
        showing?.player = null
        showing = null
        player?.release()
        player = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        showing = null
        pager = null
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) player?.pause() else player?.play()
    }

    private fun playPage(position: Int, retry: Boolean = true) {
        val active = player ?: return
        val page = (pager?.getChildAt(0) as? RecyclerView)
            ?.findViewHolderForAdapterPosition(position) as? ReelsAdapter.Holder
        if (page == null) {
            if (retry) pager?.post { playPage(position, retry = false) }
            return
        }
        showing?.player = null
        page.b.player.player = active
        showing = page.b.player
        active.setMediaItem(MediaItem.fromUri(raw(FakeData.reels[position].video)))
        active.prepare()
        active.playWhenReady = !isHidden
    }

    private fun raw(resId: Int) = "android.resource://${requireContext().packageName}/$resId"
}
