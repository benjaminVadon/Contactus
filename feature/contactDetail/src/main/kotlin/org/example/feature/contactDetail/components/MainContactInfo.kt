package org.example.feature.contactDetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.utils.designSystem.AppThemeForPreview
import org.example.utils.sharedResources.R

@Composable
internal fun RowScope.MainContactInfo(age: String, gender: String, nationality: String) {
    Column(
        modifier = Modifier
            .weight(1f)
            .align(Alignment.CenterVertically),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        InfoBlock(topTextResId = R.string.age, bottomText = age)
        InfoBlock(topTextResId = R.string.gender, bottomText = gender)
        InfoBlock(topTextResId = R.string.nationality, bottomText = nationality)
    }
}

@Preview
@Composable
private fun MainContactInfoPreview() = AppThemeForPreview {
    Row {
        MainContactInfo(
            age = "20",
            gender = "Male",
            nationality = "American"
        )
    }
}