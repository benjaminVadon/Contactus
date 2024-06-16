package org.example.feature.contactList.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.utils.designSystem.AppThemeForPreview

@Composable
internal fun Loader(modifier: Modifier = Modifier) =
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator(modifier = Modifier.padding(bottom = 16.dp))
        Text(
            text = "Loading",
            style = MaterialTheme.typography.titleLarge,
        )
    }

@Preview
@Composable
private fun LoaderPreview() = AppThemeForPreview {
    Loader()
}