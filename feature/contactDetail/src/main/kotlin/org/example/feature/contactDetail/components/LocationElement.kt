package org.example.feature.contactDetail.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.utils.designSystem.AppThemeForPreview
import org.example.utils.sharedResources.R

@Composable
internal fun LocationElement(@StringRes name: Int, value: String) {
    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            modifier = Modifier.alignByBaseline(),
            text = stringResource(id = name),
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            modifier = Modifier.alignByBaseline(),
            text = value,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
@Preview
private fun LocationElementPreview() = AppThemeForPreview {
    LocationElement(name = R.string.country, value = "United States")
}