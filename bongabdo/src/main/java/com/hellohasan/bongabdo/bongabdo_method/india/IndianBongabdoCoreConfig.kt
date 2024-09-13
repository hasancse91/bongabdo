package com.hellohasan.bongabdo.bongabdo_method.india

import com.hellohasan.bongabdo.bongabdo_method.BongabdoCoreConfig

class IndianBongabdoCoreConfig : BongabdoCoreConfig() {
    override val nonLeapYearMonthLength: List<Int> =
        listOf()
    override val leapYearMonthLength: List<Int>
        get() = listOf()
}