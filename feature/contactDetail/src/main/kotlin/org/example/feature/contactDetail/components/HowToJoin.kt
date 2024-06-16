package org.example.feature.contactDetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.utils.designSystem.AppThemeForPreview
import org.example.utils.sharedResources.R

@Composable
internal fun HowToJoin(userName: String, phone: String, cell: String, email: String) {
    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Contact(icon = Icons.Filled.AlternateEmail, name = R.string.userName, value = userName)
            Contact(icon = Icons.Filled.Phone, name = R.string.phone, value = phone)
            Contact(icon = Icons.Filled.Smartphone, name = R.string.cell, value = cell)
            Contact(icon = Icons.Filled.Email, name = R.string.email, value = email)
        }
    }
}

@Composable
@Preview
private fun HowToJoinPreview() = AppThemeForPreview {
    HowToJoin(userName = "userName", phone = "phone", cell = "cell", email = "email")
}