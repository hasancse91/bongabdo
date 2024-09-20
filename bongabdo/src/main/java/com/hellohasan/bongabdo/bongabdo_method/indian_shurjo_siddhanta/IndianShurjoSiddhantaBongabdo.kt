package com.hellohasan.bongabdo.bongabdo_method.indian_shurjo_siddhanta

import com.hellohasan.bongabdo.api.Bongabdo
import com.hellohasan.bongabdo.api.BongabdoData
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.floor

class IndianShurjoSiddhantaBongabdo: Bongabdo() {

    private var mn = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")

    private val masLen = listOf(
        0.0,
        30.92569444,
        62.63289352,
        94.00184028,
        125.4761458,
        156.4885417,
        186.9247338,
        216.8066667,
        246.3155787,
        275.6427546,
        305.0935301,
        334.9103588,
        365.2587564814815
    )

    override fun getBongabdoData(calendar: Calendar): BongabdoData {
        calendar.timeInMillis += (calendar.timeZone.rawOffset + 360 * 60 * 1000).toLong()

        val eday = calendar.get(Calendar.DAY_OF_MONTH)
        val emonth = calendar.get(Calendar.MONTH) + 1
        val eyear = calendar.get(Calendar.YEAR)

        val bcal = banglaDate(eyear, emonth, eday) ?: Triple(0, 0, 0)

        // Determine season, seasonName, yearName, monthName, dayName as per your requirements
        // Assuming season is based on month, you might need to define how seasons are determined

        val season = when (bcal.second) {
            in 1..3 -> 1 // Example: Spring
            in 4..6 -> 2 // Summer
            in 7..9 -> 3 // Autumn
            in 10..12 -> 4 // Winter
            else -> 0
        }

        val seasonName = when (season) {
            1 -> "Spring"
            2 -> "Summer"
            3 -> "Autumn"
            4 -> "Winter"
            else -> "Unknown"
        }

        val yearName =  mLocalizationConfig.toLocalizedNumber(bcal.first.toString())
        val monthName = mLocalizationConfig.monthNameList[bcal.second - 1] ?: "Unknown"
        val dayName = mLocalizationConfig.toLocalizedNumber(bcal.third) // Example logic; adjust as needed

        // Create a Calendar instance
        val gregorianCalendar = Calendar.getInstance()
        gregorianCalendar.set(eyear, emonth - 1, eday)

        return BongabdoData(
            calendar = gregorianCalendar,
            season = season,
            year = bcal.first,
            month = bcal.second,
            day = bcal.third,
            seasonName = seasonName,
            yearName = yearName,
            monthName = monthName,
            dayName = dayName
        )
    }

    private fun modernDateToJulianDay(year: Int, month: Int, day: Int): Double {
        var eYear = year
        var eMonth = month
        var eDay = day

        if (eMonth < 3) {
            eYear -= 1
            eMonth += 12
        }

        var julianDay = Math.floor(365.25 * eYear) + Math.floor(30.59 * (eMonth - 2)) + eDay + 1721086.5

        if (eYear < 0) {
            julianDay -= 1
            if ((eYear % 4 == 0) && (3 <= eMonth)) {
                julianDay += 1
            }
        }

        if (julianDay > 2299160) {
            julianDay += floor(eYear / 400.0) - floor(eYear / 100.0) + 2
        }

        return julianDay
    }

    /**
     * Converts a Gregorian date to Bangla Date.
     */
    fun banglaDate(year: Int, month: Int, day: Int): Triple<Int, Int, Int>? {

        val country = "India"
        val startJD: Double = if (country == "India") {
            1938094.4629
        } else {
            1938094.483733333
        }

        val nJD = modernDateToJulianDay(year, month, day)
        if (nJD < startJD) {
            println("Date is not appropriate.")
            return null
        }

        val jddiff = nJD - startJD
        val lastEYear = floor(jddiff / masLen[12]).toInt()
        val mesh = startJD + lastEYear * masLen[12]
        var lastDay = 0.0
        var beMonth = 0
        var beDay = 0

        for (i in 0 until 12) {
            val ps = mesh + masLen[i]
            val ns = mesh + masLen[i + 1]
            if (nJD in ps..(floor(ns) + 1.75)) {
                beMonth = i + 1
                beDay = floor(nJD - ps).toInt() + 1
            }
        }

        // Generate bmonth_len as a comma-separated string of dates
        val array = mutableListOf<String>()
        for (i in 0 until 12) {
            val lastday = mesh + masLen[i]
            val nda = calData(lastday + 1)
            val formattedDate = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).format(nda)
            array.add(formattedDate)
        }
        return Triple(lastEYear + 1, beMonth, beDay)
    }

    /**
     * Converts Julian Day to Gregorian Date.
     */
    private fun calData(jd: Double): Date {
        var z1 = jd + 0.5
        val z2 = Math.floor(z1).toInt()
        val f = z1 - z2

        var a = z2
        if (z2 >= 2299161) {
            val alpha = Math.floor((z2 - 1867216.25) / 36524.25).toInt()
            a = z2 + 1 + alpha - Math.floor(alpha / 4.0).toInt()
        }

        val b = a + 1524
        val c = Math.floor((b - 122.1) / 365.25).toInt()
        val d = Math.floor(365.25 * c).toInt()
        val e = Math.floor((b - d) / 30.6001).toInt()

        var day = b - d - Math.floor(30.6001 * e).toInt() + f
        val kday = Math.floor(day).toInt()

        var kmon = if (e < 13.5) e - 1 else e - 13
        val kyear = if (kmon > 2.5) c - 4716 else c - 4715

        // Convert to Date
        val dateStr = "${mn[kmon - 1]} ${kday}, ${kyear} 00:00:00"
        val sdf = SimpleDateFormat("MMMM d, yyyy HH:mm:ss", Locale.ENGLISH)
        return sdf.parse(dateStr) ?: Date()
    }

}