package com.hellohasan.bongabdo.bongabdo_method.india

import com.hellohasan.bongabdo.api.Bongabdo
import com.hellohasan.bongabdo.api.BongabdoData
import com.hellohasan.bongabdo.bongabdo_method.BongabdoCoreConfig
import java.util.Calendar
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.sin

class IndiaBongabdo : Bongabdo() {

    override fun getBongabdoCoreConfig(): BongabdoCoreConfig {
        return IndianBongabdoCoreConfig()
    }

    override fun getBongabdoData(calendar: Calendar): BongabdoData {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DATE)

        // Get the Julian day for the date provided
        val jd = mdyToJulian(month + 1, day, year)

        // Adjust for timezone, daylight savings, etc.
        val bongabdoYear = calculateBanglaYear(jd)
        val bongabdoMonthIndex = calculateBanglaMonth(jd)
        val bongabdoDate = calculateBanglaDay(jd)

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

    private fun mdyToJulian(m: Int, d: Int, y: Int): Double {
        val im = 12 * (y + 4800) + m - 3
        var j = (2 * (im - (im / 12) * 12) + 7 + 365 * im) / 12
        j = floor(j.toDouble()).toInt() + d + (im / 48) - 32083
        return if (j > 2299171.0) j + (im / 4800.0) - (im / 1200F) + 38F else j.toDouble() - 0.5
    }

    private fun calculateBanglaYear(jd: Double): Int {
        val banglaStartYear = 594 - 1
        val currentGregorian = jdToGregorian(jd)
        val banglaYear = currentGregorian[0] - banglaStartYear

        return if (PoilaMas(mdyToJulian(4, 20, currentGregorian[0])) > jd) {
            banglaYear - 1
        } else {
            banglaYear
        }
    }

    private fun calculateBanglaMonth(jd: Double): Int {
        val Lsun = sun(jd)
        val ayanamsa = calcayan(jd)
        return floor(fix360(Lsun + ayanamsa) / 30).toInt()
    }

    private fun calculateBanglaDay(jd: Double): Int {
        val jdStart = PoilaMas(jd)
        return floor(jd - jdStart + 1).toInt()
    }

    private fun PoilaMas(jd: Double): Double {
        val bLsun = sun(jd)
        val bayanamsa = calcayan(jd)
        val targetLong = floor(fix360(bLsun + bayanamsa) / 30) * 30.0

        var flag = false
        var adjustedJd = jd
        while (!flag) {
            val currentLong = fix360(sun(adjustedJd) + bayanamsa)
            val diff = targetLong - currentLong
            adjustedJd += diff / moonSpeed(jd)
            flag = abs(diff) <= 0.001
        }
        return adjustedJd
    }

    private fun sun(jd: Double): Double {
        val t = (jd - 2415020) / 36525
        val t2 = t * t
        val t3 = t * t * t

        val meanLong = 279.696678 + 0.9856473354 * (jd - 2415020) + t2 / 3600
        return fix360(meanLong)
    }

    private fun calcayan(jd: Double): Double {
        val t = (jd - 2415020) / 36525
        val omega = 259.183275 - 1934.142 * t
        val longitude = 279.696678 + 36000.76892 * t
        return (17.23 * sin(Math.toRadians(omega)) + 1.27 * sin(Math.toRadians(2 * longitude)) - (5025.64 + 1.11 * t) * t) / 3600
    }

    private fun moonSpeed(jd: Double): Double {
        return 13.176397 + 1.434006 * cos(Math.toRadians(270.4341639 + 481267.8831417 * (jd - 2415020) / 36525))
    }

    private fun fix360(angle: Double): Double {
        var a = angle
        while (a < 0) a += 360
        while (a > 360) a -= 360
        return a
    }

    private fun jdToGregorian(jd: Double): Array<Int> {
        val jd0 = jd + 0.5
        val z = floor(jd0).toInt()
        val f = jd0 - z

        val a: Int
        if (z < 2299161) {
            a = z
        } else {
            val alpha = floor((z - 1867216.25) / 36524.25).toInt()
            a = z + 1 + alpha - floor(alpha / 4.0).toInt()
        }

        val b = a + 1524
        val c = floor((b - 122.1) / 365.25).toInt()
        val d = floor(365.25 * c).toInt()
        val e = floor((b - d) / 30.6001).toInt()

        val day = b - d - floor(30.6001 * e).toInt() + f
        val month = if (e < 13.5) e - 1 else e - 13
        val year = if (month > 2.5) c - 4716 else c - 4715

        return arrayOf(year, month, floor(day).toInt())
    }
}