package com.hellohasan.bongabdo.localization

/**
 * To take implementation inspiration check [BengaliLocalizationConfig] class.
 */
abstract class BongabdoLocalizationConfig {

    /**0 to 9 digit Localization map. (Ex: 0 to "০", 1 to "১"...)*/
    abstract val digitMap: Map<Int, String>

    /**12 month names in your language. List size must be 12.*/
    abstract val monthNameList: List<String>

    /**6 season names in your language. List size must be 6.*/
    abstract val seasonNameList: List<String>

    open fun toLocalizedNumber(number: Int): String {
        return toLocalizedNumber(number.toString())
    }

    open fun toLocalizedNumber(number: String): String {
        val localizedNumberStringBuilder = StringBuilder()

        number.forEach { c ->
            if (c.isDigit()) {
                localizedNumberStringBuilder.append(digitMap[c.digitToInt()])
            } else {
                localizedNumberStringBuilder.append(c)
            }
        }

        return localizedNumberStringBuilder.toString()
    }
}