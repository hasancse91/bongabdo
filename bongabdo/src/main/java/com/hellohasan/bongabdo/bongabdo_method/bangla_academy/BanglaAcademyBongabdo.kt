package com.hellohasan.bongabdo.bongabdo_method.bangla_academy

import com.hellohasan.bongabdo.api.Bongabdo
import com.hellohasan.bongabdo.api.BongabdoData
import com.hellohasan.bongabdo.bongabdo_method.BongabdoCoreConfig
import com.hellohasan.bongabdo.util.diffInDays
import java.util.Calendar
import kotlin.math.floor

class BanglaAcademyBongabdo : Bongabdo() {

    override fun getBongabdoCoreConfig(): BongabdoCoreConfig {
        return BanglaAcademyCoreConfig()
    }

    override fun getBongabdoData(calendar: Calendar): BongabdoData {

        val bongabdoYear = getBongabdoYear(calendar)

        val bongabdoMonthAndDatePair = getBongabdoMonthAndDate(calendar)

        val bongabdoMonthIndex = bongabdoMonthAndDatePair.first
        val bongabdoDate = bongabdoMonthAndDatePair.second

        val seasonIndex = floor(bongabdoMonthIndex / 2.0).toInt()

        return BongabdoData(
            calendar = calendar,
            season = seasonIndex,
            year = bongabdoYear,
            month = bongabdoMonthIndex,
            day = bongabdoDate,
            seasonName = mLocalizationConfig.seasonNameList[seasonIndex],
            yearName = mLocalizationConfig.toLocalizedNumber(bongabdoYear),
            monthName = mLocalizationConfig.monthNameList[bongabdoMonthIndex],
            dayName = mLocalizationConfig.toLocalizedNumber(bongabdoDate),
        )
    }

    private fun getBongabdoYear(calendar: Calendar): Int {
        val sanitizedGregYear = sanitizeGregYear(calendar)

        return sanitizedGregYear - 593
    }

    private fun getBongabdoMonthAndDate(calendar: Calendar): Pair<Int, Int> {
        val sanitizedGregYear = sanitizeGregYear(calendar)

        val epoch = Calendar.getInstance()
        epoch.set(sanitizedGregYear, 3, 13, 0, 0, 0)
        var dayRemaining = epoch.diffInDays(calendar)

        var bongabdoMonthIndex = 0

        val monthLengthList = mBongabdoCoreConfig.getMonthLengthList(calendar[Calendar.YEAR])
        for (i in monthLengthList.indices) {
            if (dayRemaining <= monthLengthList[i]) {
                bongabdoMonthIndex = i
                break
            }
            dayRemaining -= monthLengthList[i]
        }

        val bongabdoDate = dayRemaining.toInt()

        return Pair(bongabdoMonthIndex, bongabdoDate)
    }

    private fun sanitizeGregYear(calendar: Calendar): Int {
        val gregDate = calendar.get(Calendar.DATE)
        val gregMonth = calendar.get(Calendar.MONTH)
        val gregYear = calendar.get(Calendar.YEAR)

        // If the given date is smaller than 14th April of current Gregorian Year
        if (gregMonth < 3 || (gregMonth == 3 && gregDate < 14)) {
            // 3 is the index of 'April'
            return gregYear - 1
        }
        return gregYear
    }

}