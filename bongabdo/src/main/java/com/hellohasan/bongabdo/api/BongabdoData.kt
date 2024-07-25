package com.hellohasan.bongabdo.api

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class BongabdoData(
    val calendar: Calendar,
    val season: Int,
    val year: Int,
    val month: Int,
    val day: Int,
    val seasonName: String,
    val yearName: String,
    val monthName: String,
    val dayName: String,
) {

    override fun toString(): String {
        val format = SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH)
        return "ইংরেজি: ${format.format(calendar.time)}\n" +
                "বঙ্গাব্দ: $dayName $monthName, $yearName\n" +
                "ঋতু: $seasonName"
    }

    fun getFullDate(): String {
        return "$dayName $monthName, $yearName"
    }
}
