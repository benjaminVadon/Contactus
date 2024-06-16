package org.example.feature.contactDetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.utils.designSystem.AppThemeForPreview
import org.example.utils.sharedResources.R

@Composable
internal fun Location(country: String, state: String, city: String) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LocationElement(name = R.string.country, value = country)
            LocationElement(name = R.string.state, value = state)
            LocationElement(name = R.string.city, value = city)
        }
    }
}

@Composable
@Preview
private fun LocationPreview() = AppThemeForPreview {
    Location(country = "country", state = "state", city = "city")
}