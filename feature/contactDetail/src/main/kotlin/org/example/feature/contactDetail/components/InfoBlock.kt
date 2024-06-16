package org.example.feature.contactDetail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.example.utils.designSystem.AppThemeForPreview
import org.example.utils.sharedResources.R

@Composable
internal fun InfoBlock(topTextResId: Int, bottomText: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = bottomText,
            style = MaterialTheme.typography.headlineLarge,
        )
        Text(
            text = stringResource(id = topTextResId),
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Preview
@Composable
private fun InfoBlockPreview() = AppThemeForPreview {
    InfoBlock(topTextResId = R.string.nationality, bottomText = "French")
}