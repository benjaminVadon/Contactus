package org.example.contactus.mainActivityContent.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
        Column(modifier = Modifier.padding(innerPadding)) {
            Navigation(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                navController = rememberNavController()
            )
            if (state.showInternetConnectionIndicator) {
                InternetConnectionIndicator()
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