package com.hellohasan.bongabdosample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.hellohasan.bongabdo.api.Bongabdo
import com.hellohasan.bongabdo.api.BongabdoMethod
import com.hellohasan.bongabdo.localization_config.BengaliLocalizationConfig
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Text(
                        text = getCurrentBongabdo(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
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