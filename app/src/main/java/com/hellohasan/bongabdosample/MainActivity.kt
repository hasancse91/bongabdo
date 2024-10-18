package com.hellohasan.bongabdosample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.hellohasan.bongabdosample.screen.main.MainScreen
import com.hellohasan.bongabdosample.ui.theme.BongabdoSampleTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BongabdoSampleTheme {
                MainScreen()
            }
        }
    }
}