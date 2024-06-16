package org.example.utils.pagingItems

import androidx.annotation.IntRange
import androidx.paging.ItemSnapshotList

class SyncedItemSnapshotList<T>(
    private val itemSnapshotList: ItemSnapshotList<T>,
    private val syncPresenter: (Int) -> Unit,
) : AbstractList<T?>() {
    @get:IntRange(from = 0)
    val placeholdersBefore: Int
        get() = itemSnapshotList.placeholdersBefore

    @get:IntRange(from = 0)
    val placeholdersAfter: Int
        get() = itemSnapshotList.placeholdersAfter

    override val size: Int
        get() = itemSnapshotList.size

    override operator fun get(index: Int): T? {
        syncPresenter(index)
        return itemSnapshotList[index]
    }

    companion object {
        fun <T> initial(
            itemList: List<T> = emptyList()
        ) = SyncedItemSnapshotList(
            itemSnapshotList = ItemSnapshotList(
                placeholdersBefore = 0,
                placeholdersAfter = 0,
                items = itemList
            ),
            syncPresenter = {}
        )
    }
}