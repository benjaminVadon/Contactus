package org.example.feature.contactDetail.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.utils.designSystem.AppThemeForPreview

@Composable
internal fun HeaderName(
    lastName: String,
    firstName: String
) = Text(
    modifier = Modifier.padding(
        horizontal = 16.dp,
        vertical = 20.dp,
    ),
    text = "$lastName $firstName",
    style = MaterialTheme.typography.displayMedium,
    minLines = 2,
    maxLines = 2
)

@Composable
@Preview
private fun HeaderNamePreview() = AppThemeForPreview {
    HeaderName("Doe", "John")
}