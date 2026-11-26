package com.example.instagramclone.ui.home

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramclone.R
import com.example.instagramclone.databinding.ItemPostBinding
import com.example.instagramclone.databinding.ItemStoriesRowBinding
import com.example.instagramclone.model.Post
import com.example.instagramclone.ui.common.BurstHeart
import com.example.instagramclone.ui.common.compact

class FeedAdapter(private val posts: List<Post>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val liked = BooleanArray(posts.size)
    private val saved = BooleanArray(posts.size)

    override fun getItemCount() = posts.size + 1

    override fun getItemViewType(position: Int) = if (position == 0) TYPE_STORIES else TYPE_POST

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_STORIES) {
            StoriesHolder(ItemStoriesRowBinding.inflate(inflater, parent, false))
        } else {
            PostHolder(ItemPostBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PostHolder) holder.bind(position - 1)
    }

    class StoriesHolder(b: ItemStoriesRowBinding) : RecyclerView.ViewHolder(b.root) {
        init {
            b.stories.layoutManager =
                LinearLayoutManager(b.root.context, LinearLayoutManager.HORIZONTAL, false)
            b.stories.adapter = StoryAdapter()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    inner class PostHolder(private val b: ItemPostBinding) : RecyclerView.ViewHolder(b.root) {

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
            b.photo.setOnTouchListener { _, event -> taps.onTouchEvent(event) }
            b.like.setOnClickListener { toggleLike(); pop(b.like) }
            b.save.setOnClickListener { toggleSave(); pop(b.save) }
        }

        fun bind(index: Int) {
            val post = posts[index]
            b.avatar.setImageResource(post.avatar)
            b.username.text = post.user
            b.photo.setImageResource(post.photo)
            b.caption.text = boldName(post.user, post.caption)
            b.commentCount.text = compact(post.comments)
            b.repostCount.text = compact(post.reposts)
            b.shareCount.text = compact(post.shares)
            b.time.text = post.time
            heart.reset()
            drawLike(index)
            drawSave(index)
        }

        private fun likeOnly() {
            val index = bindingAdapterPosition - 1
            if (index !in posts.indices) return
            heart.burst()
            if (liked[index]) return
            liked[index] = true
            drawLike(index)
        }

        private fun toggleLike() {
            val index = bindingAdapterPosition - 1
            if (index !in posts.indices) return
            liked[index] = !liked[index]
            drawLike(index)
        }

        private fun toggleSave() {
            val index = bindingAdapterPosition - 1
            if (index !in posts.indices) return
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
            b.likeCount.text = compact(posts[index].likes + if (on) 1 else 0)
        }

        private fun drawSave(index: Int) {
            b.save.setImageResource(
                if (saved[index]) R.drawable.ic_bookmark_filled else R.drawable.ic_bookmark
            )
        }

        private fun pop(view: View) {
            view.animate().cancel()
            view.scaleX = 0.75f
            view.scaleY = 0.75f
            view.animate()
                .scaleX(1f).scaleY(1f)
                .setStartDelay(0)
                .setDuration(240)
                .setInterpolator(OvershootInterpolator(4f))
                .start()
        }
    }

    private fun boldName(user: String, caption: String): CharSequence =
        SpannableStringBuilder(user).apply {
            setSpan(StyleSpan(Typeface.BOLD), 0, user.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            append("  ").append(caption)
        }

    private companion object {
        const val TYPE_STORIES = 0
        const val TYPE_POST = 1
    }
}
