package com.hellohasan.bongabdosample.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hellohasan.bongabdosample.LocaleManager
import com.hellohasan.bongabdosample.R

@Composable
fun SettingsScreen() {
    val context = LocalContext.current

    var selectedLocale by remember { mutableStateOf(LocaleManager.getCurrentLocale(context)) }
    val languageOptions = LocaleManager.getLanguageOptions()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = stringResource(R.string.language_preference))

        languageOptions.forEach { option ->
            LanguageRadioButton(
                option = option,
                selected = selectedLocale.language == option.locale.language,
                onSelected = {
                    selectedLocale = option.locale
                    LocaleManager.setLocale(context, selectedLocale)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}



