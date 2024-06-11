package org.example.utils.designSystem

import android.app.Activity
import android.os.Build
import android.view.View
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
    val colorScheme = colorScheme(dynamicColor, darkTheme)
    with(LocalView.current) {
        if (!isInEditMode) {
            SideEffect { setStatusBarColor(colorScheme, darkTheme) }
        }
    }
    MaterialTheme(
        colorScheme = colorScheme,
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

@Composable
private fun dynamicColorScheme(
    darkTheme: Boolean
): ColorScheme = with(LocalContext.current) {
    if (darkTheme) dynamicDarkColorScheme(this)
    else dynamicLightColorScheme(this)
}

private fun View.setStatusBarColor(colorScheme: ColorScheme, darkTheme: Boolean) {
    val window = (context as Activity).window
    window.statusBarColor = colorScheme.primary.toArgb()
    WindowCompat.getInsetsController(window, this).isAppearanceLightStatusBars = darkTheme
}
