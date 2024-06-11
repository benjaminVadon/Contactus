package org.example.feature.contactList

import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.feature.contactList.components.TopBar
import org.example.feature.contactList.contract.ContactListEvents
import org.example.feature.contactList.contract.ContactListState

@Composable
internal fun ContactListContent(
    state: ContactListState,
    processEvent: (ContactListEvents) -> Unit,
    navigation: ContactListNavigation,
) {
    Scaffold(
        modifier = Modifier
            .systemBarsPadding()
            .displayCutoutPadding(),
        topBar = { TopBar() }
    ) { paddingValues ->
        Text(modifier = Modifier.padding(paddingValues), text = "contactList")
    }
}