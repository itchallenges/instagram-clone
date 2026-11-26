package com.example.instagramclone.data

import com.example.instagramclone.R
import com.example.instagramclone.model.Chat
import com.example.instagramclone.model.Highlight
import com.example.instagramclone.model.Note
import com.example.instagramclone.model.Post
import com.example.instagramclone.model.Reel
import com.example.instagramclone.model.Story

object FakeData {

    val avatars = listOf(R.drawable.user01, R.drawable.user02, R.drawable.user03, R.drawable.user04, R.drawable.user05, R.drawable.user06,
        R.drawable.user07, R.drawable.user08, R.drawable.user09, R.drawable.user10, R.drawable.user11, R.drawable.user12,
        R.drawable.user13, R.drawable.user14, R.drawable.user15, R.drawable.user16, R.drawable.user17, R.drawable.user18)
    val photos = listOf(R.drawable.post01, R.drawable.post02, R.drawable.post03, R.drawable.post04,
        R.drawable.post05, R.drawable.post06, R.drawable.post07, R.drawable.post08)
    val covers = listOf(R.drawable.reel01, R.drawable.reel02, R.drawable.reel03, R.drawable.reel04,
        R.drawable.reel05, R.drawable.reel06, R.drawable.reel07, R.drawable.reel08)
    val videos = listOf(R.raw.reel01, R.raw.reel02, R.raw.reel03, R.raw.reel04, R.raw.reel05, R.raw.reel06, R.raw.reel07, R.raw.reel08)
    val explore = listOf(
        R.drawable.exp01, R.drawable.exp02, R.drawable.exp03, R.drawable.exp04, R.drawable.exp05, R.drawable.exp06, R.drawable.exp07, R.drawable.exp08,
        R.drawable.exp09, R.drawable.exp10, R.drawable.exp11, R.drawable.exp12, R.drawable.exp13, R.drawable.exp14, R.drawable.exp15, R.drawable.exp16,
        R.drawable.exp17, R.drawable.exp18, R.drawable.exp19, R.drawable.exp20, R.drawable.exp21, R.drawable.exp22, R.drawable.exp23, R.drawable.exp24,
        R.drawable.exp25, R.drawable.exp26, R.drawable.exp27, R.drawable.exp28, R.drawable.exp29, R.drawable.exp30, R.drawable.exp31, R.drawable.exp32,
        R.drawable.exp33, R.drawable.exp34, R.drawable.exp35, R.drawable.exp36, R.drawable.exp37, R.drawable.exp38, R.drawable.exp39, R.drawable.exp40,
        R.drawable.exp41, R.drawable.exp42, R.drawable.exp43, R.drawable.exp44, R.drawable.exp45, R.drawable.exp46, R.drawable.exp47, R.drawable.exp48,
        R.drawable.exp49, R.drawable.exp50, R.drawable.exp51, R.drawable.exp52, R.drawable.exp53, R.drawable.exp54, R.drawable.exp55, R.drawable.exp56,
        R.drawable.exp57, R.drawable.exp58, R.drawable.exp59, R.drawable.exp60)

    private val names = listOf("torvalds", "maya.codes", "jonah_lens", "sofia.travels", "dev.with.rae", "kai_builds",
        "noor.frames", "leo.makes", "ivy.designs", "theo.runs", "mila.paints", "arjun.eats")
    private val captions = listOf("golden hour never misses", "shipped it at 3am, worth it", "somewhere off the map",
        "studio day, no notes", "rooftop light hits different", "took the long way home", "found this trail by accident", "print day at the shop")
    private val times = listOf("2 HOURS AGO", "5 HOURS AGO", "11 HOURS AGO", "YESTERDAY", "1 DAY AGO", "2 DAYS AGO", "3 DAYS AGO", "5 DAYS AGO")
    private val tracks = listOf("midnight drive", "slow shutter", "coastal", "neon rain", "paper planes", "afterglow", "low tide", "static bloom")
    private val reelOwner = "itchallenges"
    private val reelCaptions = listOf("day 47. still counting from zero", "the test passed. that is the bug",
        "binary search, one wrong guess at a time", "one semicolon. the review took a week", "asked the rubber duck. the duck was right",
        "recursion, explained by starting over", "works on my machine. shipping the machine", "off by one, as usual")

    private val handles = listOf("mm.maintainer", "patch.queue", "bisect.log", "dive.log", "merge.window", "lkml.lurker",
        "doc.typos", "fs.review", "tux.plush", "git.blame", "stable.tree", "rc.tester",
        "tab.width", "subsurface.dev", "userspace.dev", "kernel.newbie", "checkpatch.pl", "minix.user")
    private val previews = listOf("So no -rc8 this time?", "Sent a reel by $reelOwner", "Bisected it. Lands on your commit.",
        "You: Bringing the laptop.", "Closed.", "Read all 312 replies to the tabs thread and nobody changed their mind, including you.",
        "You: Applied.", "Liked a message", "You: Careful, they bite.", "You wrote this. There is a receipt.",
        "Reverted.", "Is -rc1 supposed to boot?", "You: Eight.", "Your dive log has a merge conflict.",
        "You: We do not break userspace.", "Where do I send my first patch?",
        "WARNING: line length of 102 exceeds 100 columns", "You: Just a hobby, won't be big.")
    private val ago = listOf("14m", "1h", "2h", "5h", "8h", "1d", "1d", "2d", "4d", "1w", "1w", "2w", "2w", "3w", "5w", "8w", "12w", "20w")
    private val noteTexts = listOf("-rc7 is boring", "Rebasing. Again.", "Offline till Monday", "Compiling", "No merges today",
        "Read-only", "Typo in my typo fix", "Journaling")

    val stories = List(11) { Story(names[it + 1], avatars[it + 1], it >= 9) }
    val posts = List(8) { Post(names[it + 1], avatars[it + 1], photos[it], captions[it], times[it],
        1147 + it * 3371, 12 + it * 37, 4 + it * 19, 31 + it * 23) }
    val reels = List(8) { Reel(reelOwner, R.drawable.itchallenges_logo, covers[it], videos[it], reelCaptions[it],
        "$reelOwner · ${tracks[it]}", 9204 + it * 4113, 112 + it * 29, 47 + it * 31, 230 + it * 67, 1180 + it * 213) }
    val highlights = listOf(Highlight("Merges", R.drawable.hl01), Highlight("Rants", R.drawable.hl02), Highlight("Diving", R.drawable.hl03))
    val chats = List(18) { Chat(handles[it], avatars[it], previews[it], ago[it], it < 3) }
    val notes = List(8) { Note(chats[it].name, chats[it].avatar, noteTexts[it]) }

    val me = names[0]
    val myAvatar = R.drawable.avatar_tux
    const val FULL_NAME = "Linus Torvalds"
    const val BIO = "Talk is cheap. Show me the code."
    const val LINK = "kernel.org"
    const val POSTS = "1991"
    const val FOLLOWERS = 640_000
    const val FOLLOWING = 1
    val grid = photos + explore.take(10)
}
