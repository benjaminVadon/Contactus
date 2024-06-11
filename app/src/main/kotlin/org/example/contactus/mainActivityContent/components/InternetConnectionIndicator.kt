package org.example.contactus.mainActivityContent.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.utils.designSystem.AppThemeForPreview

@Composable
internal fun InternetConnectionIndicator() = Box(
    modifier =
    Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
) {
    Text(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(50))
            .background(MaterialTheme.colorScheme.errorContainer)
            .padding(horizontal = 16.dp, vertical = 4.dp),
        text = "Offline mode",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onErrorContainer
    )
}

@Preview
@Composable
private fun InternetConnectionIndicatorPreview() = AppThemeForPreview {
    InternetConnectionIndicator()
}