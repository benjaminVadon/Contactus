package org.example.utils.designSystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier

@Composable
fun AppThemeForPreview(content: @Composable () -> Unit) {
    AppTheme {
        val containerColor = MaterialTheme.colorScheme.background
        val contentColor = contentColorFor(containerColor)
        CompositionLocalProvider(LocalContentColor provides contentColor) {
            Box(modifier = Modifier.background(containerColor)) {
                content()
            }
        }
    }
}