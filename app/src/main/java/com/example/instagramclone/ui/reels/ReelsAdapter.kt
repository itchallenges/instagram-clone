package com.example.instagramclone.ui.reels

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramclone.R
import com.example.instagramclone.databinding.ItemReelBinding
import com.example.instagramclone.model.Reel
import com.example.instagramclone.ui.common.BurstHeart

class ReelsAdapter(private val reels: List<Reel>) : RecyclerView.Adapter<ReelsAdapter.Holder>() {

    private val liked = BooleanArray(reels.size)
    private val saved = BooleanArray(reels.size)

    @SuppressLint("ClickableViewAccessibility")
    inner class Holder(val b: ItemReelBinding) : RecyclerView.ViewHolder(b.root) {

        private val heart = BurstHeart(b.heart)

        private val taps = GestureDetector(
            b.root.context,
            object : GestureDetector.SimpleOnGestureListener() {
                override fun onDown(e: MotionEvent) = true

                override fun onDoubleTap(e: MotionEvent): Boolean {
                    likeOnly()
                    return true
                }
            }
        )

        init {
            b.root.setOnTouchListener { _, event -> taps.onTouchEvent(event) }
            b.like.setOnClickListener { toggleLike(); pop(b.like) }
            b.save.setOnClickListener { toggleSave(); pop(b.save) }
        }

        fun bind(index: Int) {
            val reel = reels[index]
            b.cover.setImageResource(reel.cover)
            b.audioCover.setImageResource(reel.cover)
            b.avatar.setImageResource(reel.avatar)
            b.user.text = reel.user
            b.caption.text = reel.caption
            b.audio.text = reel.audio
            b.commentCount.text = "%,d".format(reel.comments)
            b.repostCount.text = "%,d".format(reel.reposts)
            b.shareCount.text = "%,d".format(reel.shares)
            heart.reset()
            drawLike(index)
            drawSave(index)
        }

        private fun likeOnly() {
            val index = bindingAdapterPosition
            if (index !in reels.indices) return
            heart.burst()
            if (liked[index]) return
            liked[index] = true
            drawLike(index)
        }

        private fun toggleLike() {
            val index = bindingAdapterPosition
            if (index !in reels.indices) return
            liked[index] = !liked[index]
            drawLike(index)
        }

        private fun toggleSave() {
            val index = bindingAdapterPosition
            if (index !in reels.indices) return
            saved[index] = !saved[index]
            drawSave(index)
        }

        private fun drawLike(index: Int) {
            val on = liked[index]
            b.like.setImageResource(if (on) R.drawable.ic_favorite_filled else R.drawable.ic_favorite)
            b.like.imageTintList = if (on) {
                ColorStateList.valueOf(ContextCompat.getColor(b.root.context, R.color.ig_like))
            } else {
                null
            }
            b.likeCount.text = "%,d".format(reels[index].likes + if (on) 1 else 0)
        }

        private fun drawSave(index: Int) {
            val on = saved[index]
            b.save.setImageResource(if (on) R.drawable.ic_bookmark_filled else R.drawable.ic_bookmark)
            b.saveCount.text = "%,d".format(reels[index].saves + if (on) 1 else 0)
        }

        private fun pop(view: View) {
            view.animate().cancel()
            view.scaleX = 0.75f
            view.scaleY = 0.75f
            view.animate()
                .scaleX(1f).scaleY(1f)
                .setDuration(240)
                .setInterpolator(OvershootInterpolator(4f))
                .start()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(ItemReelBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(position)

    override fun onViewRecycled(holder: Holder) {
        holder.b.player.player = null
    }

    override fun getItemCount() = reels.size
}
