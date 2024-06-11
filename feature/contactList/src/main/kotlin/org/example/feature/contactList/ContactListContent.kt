package org.example.feature.contactList

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.example.feature.contactList.contract.ContactListEvents
import org.example.feature.contactList.contract.ContactListState

@Composable
internal fun ContactListContent(
    state: ContactListState,
    processEvent: (ContactListEvents) -> Unit,
    navigation: ContactListNavigation,
) {
    Text("contactList")
}