package com.hellohasan.bongabdosample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.hellohasan.bongabdo.api.Bongabdo
import com.hellohasan.bongabdo.api.BongabdoMethod
import com.hellohasan.bongabdo.localization_config.BengaliLocalizationConfig
import com.hellohasan.bongabdosample.screen.main.MainScreen
import com.hellohasan.bongabdosample.ui.theme.BongabdoSampleTheme
import java.util.Calendar

class MainActivity : ComponentActivity() {

    private val bongabdo: Bongabdo = Bongabdo.getInstance(BongabdoMethod.BANGLA_ACADEMY).apply {
        mLocalizationConfig = BengaliLocalizationConfig()
//        mLocalizationConfig = EnglishLocalizationConfig()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BongabdoSampleTheme {
                MainScreen()
            }
        }
    }

    private fun getCurrentBongabdo() : String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return bongabdo.getBongabdoData(year, month, day).getFullDate()
    }
}