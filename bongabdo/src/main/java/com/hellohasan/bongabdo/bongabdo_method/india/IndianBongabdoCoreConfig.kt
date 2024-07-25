package com.hellohasan.bongabdo.bongabdo_method.india

import com.hellohasan.bongabdo.bongabdo_method.BongabdoCoreConfig

class IndianBongabdoCoreConfig : BongabdoCoreConfig() {
    override val nonLeapYearMonthLength: List<Int> =
        listOf(31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 30)
    override val leapYearMonthLength: List<Int>
        get() = listOf(31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 31, 30)
}