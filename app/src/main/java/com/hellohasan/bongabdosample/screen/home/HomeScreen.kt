package com.hellohasan.bongabdosample.screen.home

import android.icu.util.Calendar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.hellohasan.bongabdosample.R

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    var calendarState by remember { mutableStateOf(Calendar.getInstance()) }

    var selectedDate by remember { mutableStateOf(getDateString(calendarState)) }

    val datePickerDialog = android.app.DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            calendarState = calendar

            selectedDate = getDateString(calendar)
        },
        calendarState.get(Calendar.YEAR),
        calendarState.get(Calendar.MONTH),
        calendarState.get(Calendar.DAY_OF_MONTH)
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Select a Gregorian Calendar Date")
        Button(
            content = { Text(text = selectedDate) },
            onClick = { datePickerDialog.show() }
        )
        Text("Bongabdo Date Output", modifier = Modifier.padding(top = 32.dp, bottom = 16.dp))
        BongabdoDateCard(R.string.bangla_academy_title, getBanglaAcademyBongabdo(calendarState))
        Spacer(modifier = Modifier.height(16.dp))
        BongabdoDateCard(R.string.drik_shiddhanta_title, getDrikShiddhantaBongabdo(calendarState))
    }
}