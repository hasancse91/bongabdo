package com.hellohasan.bongabdo.methods.banglaAcademy

import com.hellohasan.bongabdo.api.Bongabdo
import com.hellohasan.bongabdo.api.BongabdoData
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.until
import kotlin.math.floor

internal class BanglaAcademyBongabdo : Bongabdo() {

    private val banglaAcademyConfig = BanglaAcademyConfig()

    override fun getBongabdoData(year: Int, month: Int, day: Int): BongabdoData {
        val localDateTime = LocalDateTime(year, month + 1, day, 2, 0, 0)
        val bongabdoYear = getBongabdoYear(localDateTime)
        val timeZone = TimeZone.currentSystemDefault()

        val bongabdoMonthAndDatePair = getBongabdoMonthAndDate(localDateTime, timeZone)

        val bongabdoMonthIndex = bongabdoMonthAndDatePair.first
        val bongabdoDate = bongabdoMonthAndDatePair.second

        val seasonIndex = floor(bongabdoMonthIndex / 2.0).toInt()

        return BongabdoData(
            year = bongabdoYear,
            month = bongabdoMonthIndex,
            day = bongabdoDate,
            season = seasonIndex,
            seasonName = localizationConfig.seasonNameList[seasonIndex],
            yearName = localizationConfig.toLocalizedNumber(bongabdoYear),
            monthName = localizationConfig.monthNameList[bongabdoMonthIndex],
            dayName = localizationConfig.toLocalizedNumber(bongabdoDate),
            calendar = localDateTime
        )
    }

    private fun getBongabdoYear(localDateTime: LocalDateTime): Int {
        val sanitizedGregYear = sanitizeGregYear(localDateTime)
        return sanitizedGregYear - 593
    }

    private fun getBongabdoMonthAndDate(localDateTime: LocalDateTime, timeZone: TimeZone): Pair<Int, Int> {
        val sanitizedGregYear = sanitizeGregYear(localDateTime)

        val epoch = LocalDateTime(sanitizedGregYear, 4, 13, 0, 0, 0)
        val instantEpoch = epoch.toInstant(timeZone)
        val instantCurrent = localDateTime.toInstant(timeZone)
        val dayRemaining = instantEpoch.until(instantCurrent, DateTimeUnit.DAY, timeZone).toInt()

        var bongabdoMonthIndex = 0
        val monthLengthList = banglaAcademyConfig.getMonthLengthList(localDateTime.year)
        var remainingDays = dayRemaining

        for (i in monthLengthList.indices) {
            if (remainingDays <= monthLengthList[i]) {
                bongabdoMonthIndex = i
                break
            }
            remainingDays -= monthLengthList[i]
        }

        return Pair(bongabdoMonthIndex, remainingDays)
    }

    private fun sanitizeGregYear(localDateTime: LocalDateTime): Int {
        val gregDate = localDateTime.dayOfMonth
        val gregMonth = localDateTime.monthNumber
        val gregYear = localDateTime.year

        // If the given date is smaller than 14th April of current Gregorian Year
        if (gregMonth < 4 || (gregMonth == 4 && gregDate < 14)) {
            return gregYear - 1
        }
        return gregYear
    }
}