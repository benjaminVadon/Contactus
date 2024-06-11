package org.example.utils.designSystem

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colorScheme = colorScheme(dynamicColor, darkTheme),
        typography = AppTypography,
        content = content
    )
}

@Composable
private fun colorScheme(
    dynamicColor: Boolean,
    darkTheme: Boolean
) = when {
    dynamicColor && isSnowCode() -> dynamicColorScheme(darkTheme)
    darkTheme -> darkScheme
    else -> lightScheme
}

private fun isSnowCode(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

@RequiresApi(Build.VERSION_CODES.S)
@Composable
private fun dynamicColorScheme(
    darkTheme: Boolean
): ColorScheme = with(LocalContext.current) {
    if (darkTheme) dynamicDarkColorScheme(this)
    else dynamicLightColorScheme(this)
}