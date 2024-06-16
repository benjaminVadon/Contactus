package org.example.feature.contactList.contract

import androidx.compose.runtime.Stable
import androidx.paging.CombinedLoadStates
import org.example.domain.contacts.model.ContactDomain
import org.example.utils.mvi.contract.MVIState
import org.example.utils.pagingItems.LazyPagingItems
import org.example.utils.pagingItems.SyncedItemSnapshotList

internal data class ContactListState(
    @Stable val items: SyncedItemSnapshotList<ContactDomain> = SyncedItemSnapshotList.initial(),
    @Stable val loadState: CombinedLoadStates = LazyPagingItems.initialCombinedLoadState,
) : MVIState {
    fun updateItems(items: SyncedItemSnapshotList<ContactDomain>): ContactListState = copy(
        items = items
    )

    fun updateLoadState(state: CombinedLoadStates): ContactListState = copy(
        loadState = state
    )
}