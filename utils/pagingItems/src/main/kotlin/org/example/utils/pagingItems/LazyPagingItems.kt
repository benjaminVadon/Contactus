package org.example.utils.pagingItems

import androidx.compose.ui.platform.AndroidUiDispatcher
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.PagingDataEvent
import androidx.paging.PagingDataPresenter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlin.coroutines.CoroutineContext

class LazyPagingItems<T : Any>(
    private val flow: Flow<PagingData<T>>,
    private val coroutineContext: CoroutineContext = AndroidUiDispatcher.Main,
) {
    private val pagingDataPresenter = object : PagingDataPresenter<T>(
        mainContext = coroutineContext,
    ) {
        override suspend fun presentPagingDataEvent(event: PagingDataEvent<T>) {
            updateItemSnapshotList()
        }
    }

    private val localItemSnapshotListFlow = MutableStateFlow(snapShot())
    val itemSnapshotListFlow: StateFlow<SyncedItemSnapshotList<T>> = localItemSnapshotListFlow

    val loadStateFlow: StateFlow<CombinedLoadStates?>
        get() = pagingDataPresenter.loadStateFlow


    private suspend fun updateItemSnapshotList() {
        localItemSnapshotListFlow.emit(snapShot())
    }

    fun retry() = pagingDataPresenter.retry()

    fun refresh() = pagingDataPresenter.refresh()

    private fun snapShot(): SyncedItemSnapshotList<T> =
        SyncedItemSnapshotList(pagingDataPresenter.snapshot()) { pagingDataPresenter[it] }

    suspend fun collectPagingData() {
        flow.collectLatest {
            pagingDataPresenter.collectFrom(it)
        }
    }

    companion object {
        private val IncompleteLoadState = LoadState.NotLoading(false)
        val InitialLoadStates = LoadStates(
            LoadState.Loading,
            IncompleteLoadState,
            IncompleteLoadState
        )
        val initialCombinedLoadState = CombinedLoadStates(
            refresh = InitialLoadStates.refresh,
            prepend = InitialLoadStates.prepend,
            append = InitialLoadStates.append,
            source = InitialLoadStates
        )
    }
}