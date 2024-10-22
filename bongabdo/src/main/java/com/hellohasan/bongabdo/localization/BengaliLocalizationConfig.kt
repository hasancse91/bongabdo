package com.hellohasan.bongabdo.localization

class BengaliLocalizationConfig : BongabdoLocalizationConfig() {

    override val digitMap: Map<Int, String>
        get() = mapOf(
            0 to "০",
            1 to "১",
            2 to "২",
            3 to "৩",
            4 to "৪",
            5 to "৫",
            6 to "৬",
            7 to "৭",
            8 to "৮",
            9 to "৯"
        )

    override val monthNameList: List<String>
        get() = listOf(
            "বৈশাখ",
            "জ্যৈষ্ঠ",
            "আষাঢ়",
            "শ্রাবণ",
            "ভাদ্র",
            "আশ্বিন",
            "কার্তিক",
            "অগ্রহায়ণ",
            "পৌষ",
            "মাঘ",
            "ফাল্গুন",
            "চৈত্র"
        )

    override val seasonNameList: List<String>
        get() = listOf(
            "গ্রীষ্ম",
            "বর্ষা",
            "শরৎ",
            "হেমন্ত",
            "শীত",
            "বসন্ত"
        )
}