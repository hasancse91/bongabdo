package com.hellohasan.bongabdosample.screen.home

import android.icu.util.Calendar
import com.hellohasan.bongabdo.api.Bongabdo
import com.hellohasan.bongabdo.api.BongabdoMethod
import com.hellohasan.bongabdo.localization_config.BengaliLocalizationConfig
import com.hellohasan.bongabdo.localization_config.BongabdoLocalizationConfig
import com.hellohasan.bongabdo.localization_config.EnglishLocalizationConfig
import com.hellohasan.bongabdosample.LocaleManager
import java.text.SimpleDateFormat
import java.util.Locale

object BongabdoCalculation {

    /**
     * There are two available calculation methods: [BongabdoMethod.BANGLA_ACADEMY] and [BongabdoMethod.INDIAN_DRIK_SIDDHANTA] available in our library.
     * But you can extend [Bongabdo] class and implement your own logic inside [getBongabdoData] method.
     * Example: [SurjaShiddhantaBongabdo]
     */
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

    /**
     * You can implement your own localisation config class for Hindi, Arabic or any other language.
     * Just create a subclass of [BongabdoLocalizationConfig]. Then return it from this method.
     * Example: [HindiLocalisationConfig]
     * */
    private fun getLocalizationConfig(): BongabdoLocalizationConfig {
        return if (LocaleManager.getCurrentLocale().language == "bn")
            BengaliLocalizationConfig()
        else EnglishLocalizationConfig()
    }

    fun getDateString(calendar: Calendar): String {
        val formatter = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
        return formatter.format(calendar.time)
    }
}