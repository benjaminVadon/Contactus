package org.example.utils.pagingItems

import androidx.paging.ItemSnapshotList
import org.junit.Assert.assertEquals
import org.junit.Test


class SyncedItemSnapshotListTest {

    @Test
    fun testSyncPresenterCalled() {
        val items = listOf("Item 1", "Item 2", "Item 3")
        var syncIndex = -1
        val syncPresenter: (Int) -> Unit = { index -> syncIndex = index }

        val syncedList = SyncedItemSnapshotList(
            itemSnapshotList = ItemSnapshotList(0, 0, items),
            syncPresenter = syncPresenter
        )

        syncedList[1]

        assertEquals(1, syncIndex)
    }

    @Test
    fun testEmptyList() {
        val emptyList = SyncedItemSnapshotList.empty<String>()

        assertEquals(0, emptyList.size)
        assertEquals(0, emptyList.placeholdersBefore)
        assertEquals(0, emptyList.placeholdersAfter)
    }

    @Test
    fun testSizeAndPlaceholders() {
        val items = listOf("Item 1", "Item 2")
        val syncedList = SyncedItemSnapshotList(
            itemSnapshotList = ItemSnapshotList(2, 3, items),
            syncPresenter = {}
        )

        assertEquals(7, syncedList.size)
        assertEquals(2, syncedList.placeholdersBefore)
        assertEquals(3, syncedList.placeholdersAfter)
    }

    @Test
    fun testGetItem() {
        val items = listOf("Item 1", "Item 2")
        val syncedList = SyncedItemSnapshotList(
            itemSnapshotList = ItemSnapshotList(0, 0, items),
            syncPresenter = {})

        assertEquals("Item 1", syncedList[0])
        assertEquals("Item 2", syncedList[1])
    }
}