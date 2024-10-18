package com.hellohasan.bongabdosample.screen.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import com.hellohasan.bongabdosample.R

@Composable
fun LanguageRadioButton(option: LanguageOption, selected: Boolean, onSelected: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onSelected() }
            .fillMaxWidth()
    ) {
        RadioButton(selected = selected, onClick = onSelected)
        Text(text = stringResource(option.displayName))
    }
}

@Preview(showBackground = true)
@Composable
fun LanguageRadioButtonPreview() {
    LanguageRadioButton(
        option = LanguageOption(Locale("en"), R.string.english),
        selected = true,
        onSelected = {}
    )
}
