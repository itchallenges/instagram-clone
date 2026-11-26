package com.example.instagramclone.model

data class Post(val user: String, val avatar: Int, val photo: Int, val caption: String, val time: String,
                val likes: Int, val comments: Int, val reposts: Int, val shares: Int)
