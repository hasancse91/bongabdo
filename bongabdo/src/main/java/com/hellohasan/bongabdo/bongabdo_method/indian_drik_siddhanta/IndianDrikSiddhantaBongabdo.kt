package com.hellohasan.bongabdo.bongabdo_method.indian_drik_siddhanta

import com.hellohasan.bongabdo.api.Bongabdo
import com.hellohasan.bongabdo.api.BongabdoData
import java.util.Calendar
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

/**
 * Credit: https://www.ponjika.com/jsDrik.aspx
 */
internal class IndianDrikSiddhantaBongabdo : Bongabdo() {

    private val d2r = PI / 180
    private val r2d = 180 / PI

    private var ayanamsa = 0.0
    private var Lmoon = 0.0
    private var skar = 0.0
    private var kyear = 0.0
    private var kmon = 0.0
    private var kday = 0.0

    data class Corr(val mlcor: Int, val mscor: Int, val fcor: Int, val dcor: Int, val lcor: Double)
    data class Corr2(val l: Double, val ml: Int, val ms: Int, val f: Int, val d: Int)

    private val corrMoon = arrayOf(
        Corr(0, 0, 0, 4, 13.902),
        Corr(0, 0, 0, 2, 2369.912),
        Corr(1, 0, 0, 4, 1.979),
        Corr(1, 0, 0, 2, 191.953),
        Corr(1, 0, 0, 0, 22639.500),
        Corr(1, 0, 0, -2, -4586.465),
        Corr(1, 0, 0, -4, -38.428),
        Corr(1, 0, 0, -6, -0.393),
        Corr(0, 1, 0, 4, -0.289),
        Corr(0, 1, 0, 2, -24.420),
        Corr(0, 1, 0, 0, -668.146),
        Corr(0, 1, 0, -2, -165.145),
        Corr(0, 1, 0, -4, -1.877),
        Corr(0, 0, 0, 3, 0.403),
        Corr(0, 0, 0, 1, -125.154),
        Corr(2, 0, 0, 4, 0.213),
        Corr(2, 0, 0, 2, 14.387),
        Corr(2, 0, 0, 0, 769.016),
        Corr(2, 0, 0, -2, -211.656),
        Corr(2, 0, 0, -4, -30.773),
        Corr(2, 0, 0, -6, -0.570),
        Corr(1, 1, 0, 2, -2.921),
        Corr(1, 1, 0, 0, -109.673),
        Corr(1, 1, 0, -2, -205.962),
        Corr(1, 1, 0, -4, -4.391),
        Corr(1, -1, 0, 4, 0.283),
        Corr(1, -1, 0, 2, 14.577),
        Corr(1, -1, 0, 0, 147.687),
        Corr(1, -1, 0, -2, 28.475),
        Corr(1, -1, 0, -4, 0.636),
        Corr(0, 2, 0, 2, -0.189),
        Corr(0, 2, 0, 0, -7.486),
        Corr(0, 2, 0, -2, -8.096),
        Corr(0, 0, 2, 2, -5.741),
        Corr(0, 0, 2, 0, -411.608),
        Corr(0, 0, 2, -2, -55.173),
        Corr(0, 0, 2, -4, 0.025),
        Corr(1, 0, 0, 1, -8.466),
        Corr(1, 0, 0, -1, 18.609),
        Corr(1, 0, 0, -3, 3.215),
        Corr(0, 1, 0, 1, 18.023),
        Corr(0, 1, 0, -1, 0.560),
        Corr(3, 0, 0, 2, 1.060),
        Corr(3, 0, 0, 0, 36.124),
        Corr(3, 0, 0, -2, -13.193),
        Corr(3, 0, 0, -4, -1.187),
        Corr(3, 0, 0, -6, -0.293),
        Corr(2, 1, 0, 2, -0.290),
        Corr(2, 1, 0, 0, -7.649),
        Corr(2, 1, 0, -2, -8.627),
        Corr(2, 1, 0, -4, -2.740),
        Corr(2, -1, 0, 2, 1.181),
        Corr(2, -1, 0, 0, 9.703),
        Corr(2, -1, 0, -2, -2.494),
        Corr(2, -1, 0, -4, 0.360),
        Corr(1, 2, 0, 0, -1.167),
        Corr(1, 2, 0, -2, -7.412),
        Corr(1, 2, 0, -4, -0.311),
        Corr(1, -2, 0, 2, 0.757),
        Corr(1, -2, 0, 0, 2.580),
        Corr(1, -2, 0, -2, 2.533),
        Corr(0, 3, 0, -2, -0.344),
        Corr(1, 0, 2, 2, -0.992),
        Corr(1, 0, 2, 0, -45.099),
        Corr(1, 0, 2, -2, -0.179),
        Corr(1, 0, -2, 2, -6.382),
        Corr(1, 0, -2, 0, 39.528),
        Corr(1, 0, -2, -2, 9.366),
        Corr(0, 1, 2, 0, 0.415),
        Corr(0, 1, 2, -2, -2.152),
        Corr(0, 1, -2, 2, -1.440),
        Corr(0, 1, -2, -2, 0.384),
        Corr(2, 0, 0, 1, -0.586),
        Corr(2, 0, 0, -1, 1.750),
        Corr(2, 0, 0, -3, 1.225),
        Corr(1, 1, 0, 1, 1.267),
        Corr(1, -1, 0, -1, -1.089),
        Corr(0, 0, 2, -1, 0.584),
        Corr(4, 0, 0, 0, 1.938),
        Corr(4, 0, 0, -2, -0.952),
        Corr(3, 1, 0, 0, -0.551),
        Corr(3, 1, 0, -2, -0.482),
        Corr(3, -1, 0, 0, 0.681),
        Corr(2, 0, 2, 0, -3.996),
        Corr(2, 0, 2, -2, 0.557),
        Corr(2, 0, -2, 2, -0.459),
        Corr(2, 0, -2, 0, -1.298),
        Corr(2, 0, -2, -2, 0.538),
        Corr(1, 1, -2, -2, 0.426),
        Corr(1, -1, 2, 0, -0.304),
        Corr(1, -1, -2, 2, -0.372),
        Corr(0, 0, 4, 0, 0.418),
        Corr(2, -1, 0, -1, -0.352),
    )
    private val corrMoon2 = arrayOf(
        Corr2(0.127, 0, 0, 0, 6),
        Corr2(-0.151, 0, 2, 0, -4),
        Corr2(-0.085, 0, 0, 2, 4),
        Corr2(0.150, 0, 1, 0, 3),
        Corr2(-0.091, 2, 1, 0, -6),
        Corr2(-0.103, 0, 3, 0, 0),
        Corr2(-0.301, 1, 0, 2, -4),
        Corr2(0.202, 1, 0, -2, -4),
        Corr2(0.137, 1, 1, 0, -1),
        Corr2(0.233, 1, 1, 0, -3),
        Corr2(-0.122, 1, -1, 0, 1),
        Corr2(-0.276, 1, -1, 0, -3),
        Corr2(0.255, 0, 0, 2, 1),
        Corr2(0.254, 0, 0, 2, -3),
        Corr2(-0.100, 3, 1, 0, -4),
        Corr2(-0.183, 3, -1, 0, -2),
        Corr2(-0.297, 2, 2, 0, -2),
        Corr2(-0.161, 2, 2, 0, -4),
        Corr2(0.197, 2, -2, 0, 0),
        Corr2(0.254, 2, -2, 0, -2),
        Corr2(-0.250, 1, 3, 0, -2),
        Corr2(-0.123, 2, 0, 2, 2),
        Corr2(0.173, 2, 0, -2, -4),
        Corr2(0.263, 1, 1, 2, 0),
        Corr2(0.130, 3, 0, 0, -1),
        Corr2(0.113, 5, 0, 0, 0),
        Corr2(0.092, 3, 0, 2, -2),
    )

    override fun getBongabdoData(calendar: Calendar): BongabdoData {
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val mon = calendar.get(Calendar.MONTH) + 1
        val year = calendar.get(Calendar.YEAR)
        val hr = calendar.get(Calendar.HOUR_OF_DAY) + calendar.get(Calendar.MINUTE) / 60.0
        val tzone = calendar.timeZone.rawOffset / (1000.0 * 60.0 * 60.0)

        mdy2julian(mon, day + hr / 24, year)
        val jd0 = mdy2julian(mon, day.toDouble(), year)
        val jdut = jd0 + (hr - tzone) / 24.0
        dTime(jdut)
        val jd = jdut + 6 / 24.0

        ayanamsa = calcayan(jd)
        Lmoon = moon(jd)
        sun(jd)

        val bjdut = jd0 + (6 - 6) / 24.0
        val bjd = bjdut + 6 / 24.0
        val (bongabdoDate: Int, bongabdoMonthIndex: Int, bongabdoYear: Int) = banglaMas(bjd)

        val seasonIndex = (floor(bongabdoMonthIndex / 2.0).toInt()) % 6
        val seasonName = mLocalizationConfig.seasonNameList[seasonIndex]
        val yearName = mLocalizationConfig.toLocalizedNumber(bongabdoYear)
        val monthName = mLocalizationConfig.monthNameList[bongabdoMonthIndex]
        val dayName = mLocalizationConfig.toLocalizedNumber(bongabdoDate)

        val bongabdoData = BongabdoData(
            calendar = calendar,
            season = seasonIndex,
            year = bongabdoYear,
            month = bongabdoMonthIndex,
            day = bongabdoDate,
            seasonName = seasonName,
            yearName = yearName,
            monthName = monthName,
            dayName = dayName,
        )

        return bongabdoData
    }

    private fun banglaMas(jd: Double): Triple<Int, Int, Int> {
        val San = 594 - 1
        val date1 = jdToGregorian(jd + 6 / 24.0)
        var cyear = date1.get(Calendar.YEAR)
        val pb = mdy2julian(4, 20.0, cyear) + 0.25
        val pm = poilaMas(pb)
        if (pm > jd) {
            cyear -= 1
        }
        val byear = cyear - San

        // Calculate date2, cm2, tm2
        val date2 = jdToGregorian(jd - 1 + 6 / 24.0)
        val cm2 = mdy2julian(
            date2.get(Calendar.MONTH) + 1,
            date2.get(Calendar.DAY_OF_MONTH).toDouble(),
            date2.get(Calendar.YEAR)
        ) + 0.25
        val tm2 = poilaMas(cm2)

        // Continue with cm1 and tm1
        val cm1 = mdy2julian(
            date1.get(Calendar.MONTH) + 1,
            date1.get(Calendar.DAY_OF_MONTH).toDouble(),
            date1.get(Calendar.YEAR)
        ) + 0.25
        val tm1 = poilaMas(cm1)


        var st = floor(jd) - floor(tm1) + 1
        val bLsun = sun(jd)
        val bayanamsa = calcayan(jd)
        var bmon = floor(fix360(bLsun + bayanamsa) / 30) + 1

        if (floor(tm1) > floor(jd)) {
            st = floor(jd) - floor(tm2) + 1
            bmon -= 1
        }
        // Adjust bmon to handle negative index
        var bmonIndex = bmon.toInt() - 1
        if (bmonIndex == -1) {
            bmonIndex = 11
        }
        return Triple(st.toInt(), bmonIndex, byear)
    }

    private fun poilaMas(jd: Double): Double {
        var jdt = jd
        val bLsun = sun(jdt)
        val bayanamsa = calcayan(jdt)
        var zs = floor(fix360(bLsun + bayanamsa) / 30) + 1
        zs -= 1
        var st = 0.0
        for (izs in zs.toInt()..(zs + 1).toInt()) {
            val n1 = izs * 30.0
            var flag = false
            while (!flag) {
                val bLsun0 = fix360(sun(jdt) + bayanamsa)
                var asp1 = n1 - bLsun0
                if (asp1 > 180) asp1 -= 360.0
                if (asp1 < -180) asp1 += 360.0
                flag = true
                if (abs(asp1) > 0.001) {
                    jdt += asp1 / skar
                    flag = false
                }
            }
            if (izs == zs.toInt()) {
                val greg = jdToGregorian(jdt + 6 / 24.0)
                val date1 = greg
                st = mdy2julian(
                    date1.get(Calendar.MONTH) + 1,
                    date1.get(Calendar.DAY_OF_MONTH) + 1.25,
                    date1.get(Calendar.YEAR)
                )
            }
        }
        return st
    }

    private fun jdToGregorian(jd: Double): Calendar {
        val jd0 = jd + 0.5
        val z = floor(jd0)
        val f = jd0 - z
        val a: Double
        if (z < 2299161) {
            a = z
        } else {
            val alpha = floor((z - 1867216.25) / 36524.25)
            a = z + 1 + alpha - floor(alpha / 4.0)
        }
        val b = a + 1524
        val c = floor((b - 122.1) / 365.25)
        val d = floor(365.25 * c)
        val e = floor((b - d) / 30.6001)
        val dayt = b - d - floor(30.6001 * e) + f
        val month = if (e < 13.5) e - 1 else e - 13
        val year = if (month > 2.5) c - 4716 else c - 4715
        val day = floor(dayt).toInt()
        val calendar = Calendar.getInstance()
        calendar.set(year.toInt(), month.toInt() - 1, day)
        return calendar
    }

    private fun fix360(v: Double): Double {
        var value = v
        while (value < 0.0) value += 360.0
        while (value > 360.0) value -= 360.0
        return value
    }

    private fun mdy2julian(m: Int, d: Double, y: Int): Double {
        val im = 12 * (y + 4800) + m - 3
        var j = (2 * (im - floor(im / 12.0) * 12) + 7 + 365 * im) / 12.0
        j = floor(j) + d + floor(im / 48.0) - 32083
        if (j > 2299171) j += floor(im / 4800.0) - floor(im / 1200.0) + 38
        j -= 0.5
        return j
    }

    private fun dTime(jd: Double): Double {
        // Delta T values from 1620 to 2010 (seconds)
        val efdt = arrayOf(
            124.0, 85.0, 62.0, 48.0, 37.0, 26.0, 16.0, 10.0, 9.0, 10.0, 11.0, 11.0,
            12.0, 13.0, 15.0, 16.0, 17.0, 17.0, 13.7, 12.5, 12.0, 7.5, 5.7, 7.1,
            7.9, 1.6, -5.4, -5.9, -2.7, 10.5, 21.2, 24.0, 24.3, 29.2, 33.2, 40.2,
            50.5, 56.9, 65.7, 75.5
        )

        calData(jd)
        val dgod = kyear + (kmon - 1) / 12.0 + (kday - 1) / 365.25
        val t = (jd - 2378497) / 36525.0

        val dt: Double
        if (dgod >= 1620 && dgod < 2010) {
            val i1 = floor((dgod - 1620) / 10).toInt()
            val di = dgod - (1620 + i1 * 10)
            dt = efdt[i1] + ((efdt[i1 + 1] - efdt[i1]) * di) / 10
        } else {
            dt = when {
                dgod >= 2010 -> 25.5 * t * t - 39
                dgod >= 948 && dgod < 1620 -> 25.5 * t * t
                else -> 1361.7 + 320 * t + 44.3 * t * t
            }
        }

        return dt / 3600.0 // Convert from seconds to hours
    }

    private fun calData(jd: Double): Unit {
        val z1 = jd + 0.5
        val z2 = floor(z1)
        val f = z1 - z2
        val a: Double
        if (z2 < 2299161) {
            a = z2
        } else {
            val alf = floor((z2 - 1867216.25) / 36524.25)
            a = z2 + 1 + alf - floor(alf / 4.0)
        }
        val b = a + 1524
        val c = floor((b - 122.1) / 365.25)
        val d = floor(365.25 * c)
        val e = floor((b - d) / 30.6001)
        val days = b - d - floor(30.6001 * e) + f
        kday = floor(days)
        kmon = if (e < 13.5) e - 1 else e - 13
        kyear = if (kmon > 2.5) c - 4716 else c - 4715
    }

    private fun calcayan(jd: Double): Double {
        val t = (jd - 2415020.0) / 36525.0
        val om =
            259.183275 - 1934.142008333206 * t + 0.0020777778 * t * t + 0.0000022222222 * t * t * t
        val ls = 279.696678 + 36000.76892 * t + 0.0003025 * t * t
        var aya = 17.23 * sin(d2r * om) + 1.27 * sin(d2r * ls * 2) - (5025.64 + 1.11 * t) * t
        aya = (aya - 80861.27) / 3600.0
        return aya
    }

    private fun moon(jd: Double): Double {
        val tdays = jd - 2415020.0
        val t = tdays / 36525.0
        val t2 = t * t
        val t3 = t * t * t

        // Moon's mean longitude
        val l = 270.4341639 + 13.1763965268 * tdays - 0.0011333333 * t2 + 0.0000018888889 * t3

        // Moon's mean anomaly
        val ml = 296.104608 + 477198.849108 * t + 0.0091916667 * t2 + 0.00001438889 * t3

        // Sun's mean anomaly
        val ms = 358.475833 + 35999.049750 * t - 0.0001500000 * t2 - 0.0000033333333 * t3

        // Moon's mean distance
        val d = 350.737486 + 445267.114216 * t - 0.0014361111 * t2 + 0.0000018888889 * t3

        // Moon's mean latitude argument
        val f = 11.250889 + 483202.025150 * t - 0.0032111111 * t2 - 0.0000003333333 * t3

        // Convert to radians
        val mlRad = ml * d2r
        val msRad = ms * d2r
        val fRad = f * d2r
        val dRad = d * d2r

        // Initialize correction sums
        var lk = 0.0
        var lk1 = 0.0

        // Apply main correction terms
        for (i in corrMoon.indices) {
            val arg = corrMoon[i].mlcor * mlRad +
                    corrMoon[i].mscor * msRad +
                    corrMoon[i].fcor * fRad +
                    corrMoon[i].dcor * dRad
            val sinArg = sin(arg)
            lk += corrMoon[i].lcor * sinArg
        }

        // Apply additional correction terms
        for (i in corrMoon2.indices) {
            val arg = corrMoon2[i].ml * mlRad +
                    corrMoon2[i].ms * msRad +
                    corrMoon2[i].f * fRad +
                    corrMoon2[i].d * dRad
            val sinArg = sin(arg)
            lk1 += corrMoon2[i].l * sinArg
        }

        // Calculate Moon's ecliptic longitude
        val lMoon = l + (lk + lk1) / 3600.0

        // Moon's angular speed (approximate value)
        skar = 13.176397

        return fix360(lMoon)
    }


    private fun sun(jd: Double): Double {
        val tdays = jd - 2415020.0
        val t = tdays / 36525.0
        val t2 = t * t
        val t3 = t * t * t

        // Sun's mean longitude
        val ls = 279.696678 + 36000.76892 * t + 0.0003025 * t2

        // Sun's mean anomaly
        val ms = 358.475833 + 35999.049750 * t - 0.0001500000 * t2 - 0.0000033333333 * t3


        // The Kepler equation
        val u = kepler(ms, 0.01675104 - 0.0000418 * t - 0.000000126 * t2, 0.0000003)

        // True anomaly
        val ex = 0.01675104 - 0.0000418 * t - 0.000000126 * t2
        val b = sqrt((1 + ex) / (1 - ex))
        val truanom = 2 * atan(b * tan(u / 2))

        // Sun's true longitude
        val il = ls + (truanom * r2d) - ms

        // Nutation
        val nut = nutation(jd)

        // Aberration
        val ab = -20.5 / 3600.0

        val lSun = il + nut + ab

        // Sun's angular speed (approximate value)
        skar = 0.9856

        return fix360(lSun)
    }


    private fun nutation(jd: Double): Double {
        val t = (jd - 2415020.0) / 36525.0
        val ls = 279.6967 + 36000.7689 * t + 0.000303 * t * t
        val l = 270.4341639 + 481267.8831417 * t - 0.0011333333 * t * t
        val ms = 358.4758333 + 35999.0497499996 * t - 0.000150000006 * t * t
        val ml = 296.1046083 + 477198.849108333 * t + 0.0091916667 * t * t
        val d = 350.7374861 + 445267.114216666 * t - 0.001436111132 * t * t
        val om = 259.183275 - 1934.142008333 * t + 0.00207777778 * t * t

        val lsRad = ls * d2r
        val lRad = l * d2r
        val msRad = ms * d2r
        val mlRad = ml * d2r
        val dRad = d * d2r
        val omRad = om * d2r

        var nut = (-17.2327 - 0.01737 * t) * sin(omRad)
        nut += (-1.2729 - 0.00013 * t) * sin(2 * lsRad)
        nut += (0.2088 + 0.00013 * t) * sin(2 * omRad)
        nut += 0.0675 * sin(mlRad)
        nut += (-0.0149 + 0.00012 * t) * sin(2 * dRad - mlRad)
        nut += 0.0301 * sin(2 * dRad)
        nut += (-0.0347 + 0.0001 * t) * sin(2 * lRad)
        nut += (-0.0326 - 0.0001 * t) * sin(msRad)
        nut /= 3600.0

        return nut
    }

    private fun kepler(m: Double, ex: Double, err: Double): Double {
        val mRad = m * d2r
        var u0 = mRad
        val errRad = err * d2r
        var delta: Double
        do {
            delta = (mRad + ex * sin(u0) - u0) / (1 - ex * cos(u0))
            u0 += delta
        } while (abs(delta) >= errRad)
        return u0
    }
}