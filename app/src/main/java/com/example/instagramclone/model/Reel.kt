package com.example.instagramclone.model

data class Reel(val user: String, val avatar: Int, val cover: Int, val video: Int, val caption: String,
                val audio: String, val likes: Int, val comments: Int, val reposts: Int, val shares: Int, val saves: Int)
