package com.example.instagramclone.ui.common

import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.OvershootInterpolator

class BurstHeart(private val heart: View) {

    private val fade = Runnable {
        heart.animate()
            .alpha(0f).scaleX(1.2f).scaleY(1.2f)
            .setStartDelay(0)
            .setDuration(HEART_OUT)
            .setInterpolator(AccelerateInterpolator())
            .start()
        heart.postDelayed(rest, HEART_OUT)
    }

    private val rest = Runnable { reset() }

    fun reset() {
        heart.removeCallbacks(fade)
        heart.removeCallbacks(rest)
        heart.animate().cancel()
        heart.visibility = View.INVISIBLE
        heart.alpha = 1f
        heart.scaleX = 0.5f
        heart.scaleY = 0.5f
    }

    fun burst() {
        reset()
        heart.visibility = View.VISIBLE
        heart.animate()
            .scaleX(1f).scaleY(1f)
            .setStartDelay(0)
            .setDuration(HEART_IN)
            .setInterpolator(OvershootInterpolator())
            .start()
        heart.postDelayed(fade, HEART_IN + HEART_HOLD)
    }

    private companion object {
        const val HEART_IN = 180L
        const val HEART_HOLD = 800L
        const val HEART_OUT = 280L
    }
}
