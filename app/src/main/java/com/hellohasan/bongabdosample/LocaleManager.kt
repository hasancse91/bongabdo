package com.hellohasan.bongabdosample

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.ui.text.intl.Locale
import androidx.core.os.LocaleListCompat
import com.hellohasan.bongabdosample.screen.settings.LanguageOption

object LocaleManager {
    private const val PREFS_NAME = "app_prefs"
    private const val KEY_LOCALE = "locale"

    fun getLanguageOptions(): List<LanguageOption> {
        return listOf(
            LanguageOption(Locale("en"), R.string.english),
            LanguageOption(Locale("bn"), R.string.bangla)
        )
    }

    fun getCurrentLocale(context: Context): Locale {
        val sharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val localeTag = sharedPrefs.getString(KEY_LOCALE, "en")
        return Locale(localeTag ?: "en")
    }

    fun setLocale(context: Context, locale: Locale) {
        val sharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(sharedPrefs.edit()) {
            putString(KEY_LOCALE, locale.language)
            apply()
        }
//        updateContextLocale(context, locale)
        setApplicationLocale(locale.language)
    }

    private fun setApplicationLocale(languageShortCode: String) {
//        val languageShortCode = localeOptions[selectionLocale]
        val locale = LocaleListCompat.forLanguageTags(languageShortCode)
        AppCompatDelegate.setApplicationLocales(locale)
    }
}