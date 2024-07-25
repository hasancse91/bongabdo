package com.hellohasan.bongabdo.util

import java.util.Calendar
import java.util.concurrent.TimeUnit
import kotlin.math.abs

fun Int.isLeapYear(): Boolean {
    return ((this % 4 == 0) && (this % 100 != 0)) || (this % 400 == 0)
}

fun Calendar.diffInDays(end: Calendar): Long {
    return TimeUnit.MILLISECONDS.toDays(abs(end.timeInMillis - this.timeInMillis))
}