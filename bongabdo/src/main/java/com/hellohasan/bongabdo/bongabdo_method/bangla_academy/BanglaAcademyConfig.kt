package com.hellohasan.bongabdo.bongabdo_method.bangla_academy

internal class BanglaAcademyConfig  {

     private val nonLeapYearMonthLength: List<Int> =
        listOf(31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 29, 30)

     private val leapYearMonthLength: List<Int> =
        listOf(31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 30)

    fun getMonthLengthList(year: Int): List<Int> {
        return if (isLeapYear(year)) leapYearMonthLength else nonLeapYearMonthLength
    }

    private fun isLeapYear(year: Int): Boolean {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)
    }
}