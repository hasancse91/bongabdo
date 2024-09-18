package com.hellohasan.bongabdo.bongabdo_method.bangla_academy

import com.hellohasan.bongabdo.util.isLeapYear

class BanglaAcademyConfig  {

     private val nonLeapYearMonthLength: List<Int> =
        listOf(31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 29, 30)

     private val leapYearMonthLength: List<Int> =
        listOf(31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 30)

    fun getMonthLengthList(year: Int): List<Int> {
        return if (year.isLeapYear()) leapYearMonthLength else nonLeapYearMonthLength
    }
}