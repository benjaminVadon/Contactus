package org.example.contactus.mainActivityContent.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import org.example.contactus.mainActivityContent.contract.MainState
import org.example.utils.designSystem.AppThemeForPreview

@Composable
internal fun ContentForState(state: MainState) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Navigation(
                modifier = Modifier.fillMaxSize(),
                navController = rememberNavController()
            )
            if (state.showInternetConnectionIndicator) {
                InternetConnectionIndicator(modifier = Modifier.align(Alignment.BottomCenter))
            }
        }
    }
}

@Preview
@Composable
private fun ContentForStatePreview() = AppThemeForPreview {
    val state = MainState(true)
    ContentForState(state = state)
}