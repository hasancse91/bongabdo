package com.hellohasan.bongabdo.localization

class EnglishLocalizationConfig : BongabdoLocalizationConfig() {

    override val digitMap: Map<Int, String> = mapOf(
        0 to "0",
        1 to "1",
        2 to "2",
        3 to "3",
        4 to "4",
        5 to "5",
        6 to "6",
        7 to "7",
        8 to "8",
        9 to "9"
    )

    override val monthNameList: List<String> = listOf(
        "Baishakh",
        "Jaishtha",
        "Asharh",
        "Sraban",
        "Vadra",
        "Ashwin",
        "Karkik",
        "Agrahayan",
        "Paush",
        "Magh",
        "Falgun",
        "Chaitra"
    )

    override val seasonNameList: List<String> = listOf(
        "Summer",
        "Rainy",
        "Autumn",
        "Late Autumn",
        "Winter",
        "Spring"
    )
}