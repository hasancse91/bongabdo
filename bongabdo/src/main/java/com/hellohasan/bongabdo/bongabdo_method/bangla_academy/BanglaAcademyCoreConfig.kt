package com.hellohasan.bongabdo.bongabdo_method.bangla_academy

import com.hellohasan.bongabdo.bongabdo_method.BongabdoCoreConfig

class BanglaAcademyCoreConfig : BongabdoCoreConfig() {

    override val nonLeapYearMonthLength: List<Int> =
        listOf(31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 29, 30)

    override val leapYearMonthLength: List<Int> =
        listOf(31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 30)
}