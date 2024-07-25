package com.hellohasan.bongabdo.bongabdo_method

import com.hellohasan.bongabdo.util.isLeapYear

abstract class BongabdoCoreConfig {

    abstract val nonLeapYearMonthLength: List<Int>

    abstract val leapYearMonthLength: List<Int>

    fun getMonthLengthList(year: Int): List<Int> {
        return if (year.isLeapYear()) leapYearMonthLength else nonLeapYearMonthLength
    }
}
