package org.example.contactus.mainActivityContent.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.utils.designSystem.AppThemeForPreview
import org.example.utils.sharedResources.R

@Composable
internal fun InternetConnectionIndicator(modifier: Modifier = Modifier) = Box(
    modifier = modifier
        .fillMaxWidth()
        .padding(
            bottom = WindowInsets.navigationBars
                .asPaddingValues()
                .calculateBottomPadding()
        ),
) {
    Text(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
            .background(MaterialTheme.colorScheme.errorContainer)
            .padding(horizontal = 16.dp, vertical = 4.dp),
        text = stringResource(id = R.string.offlineMode),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onErrorContainer
    )
}

@Preview
@Composable
private fun InternetConnectionIndicatorPreview() = AppThemeForPreview {
    InternetConnectionIndicator(Modifier)
}