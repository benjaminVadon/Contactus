package org.example.feature.contactDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.domain.contacts.model.ContactDomain
import org.example.feature.contactDetail.components.BigPictureStyle
import org.example.feature.contactDetail.components.HeaderName
import org.example.feature.contactDetail.components.HowToJoin
import org.example.feature.contactDetail.components.Location
import org.example.feature.contactDetail.components.MainContactInfo
import org.example.feature.contactDetail.contract.ContactDetailState
import org.example.utils.designSystem.AppThemeForPreview

@Composable
internal fun ContactDetailContent(
    state: ContactDetailState,
) {
    Scaffold(
        contentWindowInsets = WindowInsets.ime
    ) { paddingValues ->
        state.contact?.run {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues()))
                HeaderName(lastName, firstName)
                Card(modifier = Modifier.padding(bottom = 16.dp)) {
                    Row(modifier = Modifier.padding(vertical = 40.dp)) {
                        BigPictureStyle(pictureUrl)
                        MainContactInfo(age.toString(), gender, nationality)
                    }
                }
                HowToJoin(userName = userName, phone = phone, cell = cell, email = email)
                Location(country = country, state = this@run.state, city = city)
                Spacer(modifier = Modifier.padding(WindowInsets.navigationBars.asPaddingValues()))
            }
        }
    }
}

@Composable
private fun PaddingValues.exceptTopPadding(): PaddingValues {
    val layoutDirection = LocalLayoutDirection.current

    return PaddingValues(
        top = 0.dp,
        bottom = calculateBottomPadding(),
        end = calculateEndPadding(layoutDirection),
        start = calculateStartPadding(layoutDirection)
    )
}
@Preview
@Composable
private fun ContactDetailContentPreview() = AppThemeForPreview {
    ContactDetailContent(
        state = ContactDetailState(contact = ContactDomain.Sample)
    )
}