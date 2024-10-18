package com.hellohasan.bongabdosample

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.ui.text.intl.Locale
import androidx.core.os.LocaleListCompat
import com.hellohasan.bongabdosample.screen.settings.LanguageOption

object LocaleManager {

    fun getLanguageOptions(): List<LanguageOption> {
        return listOf(
            LanguageOption(Locale("en"), R.string.english),
            LanguageOption(Locale("bn"), R.string.bangla)
        )
    }

    fun getCurrentLocale(): Locale {
        val localeList = AppCompatDelegate.getApplicationLocales()
        return Locale(localeList.get(0)?.language ?: "en")
    }

    fun setLocale(locale: Locale) {
        val mLocale = LocaleListCompat.forLanguageTags(locale.language)
        AppCompatDelegate.setApplicationLocales(mLocale)
    }

}