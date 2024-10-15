package com.hellohasan.bongabdosample.screen.home

import android.icu.util.Calendar
import com.hellohasan.bongabdo.api.Bongabdo
import com.hellohasan.bongabdo.api.BongabdoMethod
import com.hellohasan.bongabdo.localization_config.BengaliLocalizationConfig
import com.hellohasan.bongabdo.localization_config.BongabdoLocalizationConfig
import com.hellohasan.bongabdo.localization_config.EnglishLocalizationConfig
import java.text.SimpleDateFormat
import java.util.Locale

fun getBanglaAcademyBongabdo(calendar: Calendar): String {
    val bongabdo = Bongabdo.getInstance(BongabdoMethod.BANGLA_ACADEMY)
    bongabdo.mLocalizationConfig = getLocalizationConfig()
    val bongabdoDate = bongabdo.getBongabdoData(
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
    return bongabdoDate.getFullDate()
}

fun getDrikShiddhantaBongabdo(calendar: Calendar): String {
    val bongabdo = Bongabdo.getInstance(BongabdoMethod.INDIAN_DRIK_SIDDHANTA)
    bongabdo.mLocalizationConfig = getLocalizationConfig()
    val bongabdoDate = bongabdo.getBongabdoData(
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
    return bongabdoDate.getFullDate()
}

private fun getLocalizationConfig(): BongabdoLocalizationConfig {
    return if (Locale.getDefault().language == "bn")
        BengaliLocalizationConfig()
    else EnglishLocalizationConfig()
}

fun getDateString(calendar: Calendar): String {
    val formatter = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
    return formatter.format(calendar.time)
}