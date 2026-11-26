package com.example.instagramclone.ui.common

fun compact(n: Int) = when {
    n >= 1_000_000 -> scaled(n, 1_000_000, "M")
    n >= 10_000 -> scaled(n, 1_000, "K")
    else -> "%,d".format(n)
}

private fun scaled(n: Int, unit: Int, suffix: String): String {
    val tenths = (n + unit / 20) / (unit / 10)
    val whole = tenths / 10
    return if (tenths % 10 == 0) "$whole$suffix" else "$whole.${tenths % 10}$suffix"
}
