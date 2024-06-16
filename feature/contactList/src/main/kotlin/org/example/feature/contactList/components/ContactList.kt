package org.example.feature.contactList.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.domain.contacts.model.ContactDomain
import org.example.feature.contactList.contract.ContactListEvents
import org.example.utils.designSystem.AppThemeForPreview
import org.example.utils.pagingItems.SyncedItemSnapshotList

@Composable
internal fun ContactList(
    items: SyncedItemSnapshotList<ContactDomain>,
    isAppendLoading: Boolean,
    processEvent: (ContactListEvents) -> Unit,
) {
    LazyColumn {
        items(items) {
            it?.let {
                ContactItem(
                    item = it,
                    processEvent = processEvent,
                )
            }
        }
//        items(count = items.size) { index ->
//            items[index]?.let {
//                ContactItem(
//                    item = it,
//                    processEvent = processEvent,
//                )
//            }
//        }
        if (isAppendLoading) {
            item { Loader() }
        }
    }
}

@Preview
@Composable
private fun ContactListPreview() = AppThemeForPreview {
    ContactList(
        items = SyncedItemSnapshotList.initial(
            itemList = List(5) { ContactDomain.Sample }
        ),
        isAppendLoading = true,
        processEvent = {},
    )
}