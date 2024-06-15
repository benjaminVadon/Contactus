package org.example.utils.pagingItems

import androidx.annotation.IntRange
import androidx.paging.ItemSnapshotList

class SyncedItemSnapshotList<T>(
    private val itemSnapshotList: ItemSnapshotList<T>,
    private val syncPresenter: (Int) -> Unit,
) {
    @get:IntRange(from = 0)
    val placeholdersBefore: Int
        get() = itemSnapshotList.placeholdersBefore

    @get:IntRange(from = 0)
    val placeholdersAfter: Int
        get() = itemSnapshotList.placeholdersAfter

    val items: List<T>
        get() = itemSnapshotList.items

    val size: Int
        get() = itemSnapshotList.size

    operator fun get(index: Int): T? {
        syncPresenter(index)
        return itemSnapshotList.get(index)
    }

    companion object {
        fun <T> empty(): SyncedItemSnapshotList<T> = SyncedItemSnapshotList(
            itemSnapshotList = ItemSnapshotList(
                placeholdersBefore = 0,
                placeholdersAfter = 0,
                items = emptyList()
            ),
            syncPresenter = {}
        )
    }
}