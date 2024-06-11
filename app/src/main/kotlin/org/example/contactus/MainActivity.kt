package org.example.contactus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import org.example.contactus.mainActivityContent.MainActivityContent
import org.example.utils.designSystem.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        if (savedInstanceState == null) {
            installSplashScreen()
        }
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                MainActivityContent()
            }
        }
    }
}