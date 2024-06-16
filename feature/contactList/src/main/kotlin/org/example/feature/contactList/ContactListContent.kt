package org.example.feature.contactList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.paging.LoadState.NotLoading
import org.example.domain.contacts.model.ContactDomain
import org.example.feature.contactList.components.ContactList
import org.example.feature.contactList.components.Loader
import org.example.feature.contactList.components.TopBar
import org.example.feature.contactList.contract.ContactListEvents
import org.example.feature.contactList.contract.ContactListState
import org.example.utils.designSystem.AppThemeForPreview
import org.example.utils.pagingItems.LazyPagingItems.Companion.InitialLoadStates
import org.example.utils.pagingItems.SyncedItemSnapshotList

@Composable
internal fun ContactListContent(
    state: ContactListState,
    processEvent: (ContactListEvents) -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .systemBarsPadding()
            .displayCutoutPadding(),
        topBar = { TopBar() }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            with(state.loadState) {
                when (val refreshState = refresh) {
                    Loading -> Loader(
                        modifier = Modifier.align(Alignment.Center)
                    )

                    is Error -> Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = refreshState.error.message.toString(),
                    )

                    is NotLoading -> ContactList(
                        items = state.items,
                        isAppendLoading = append is Loading,
                        processEvent = processEvent,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ContactListContentPreview(@PreviewParameter(ContactListStateProvider::class) state: ContactListState) =
    AppThemeForPreview {
        ContactListContent(state = state, processEvent = {})
    }

private class ContactListStateProvider : PreviewParameterProvider<ContactListState> {
    override val values: Sequence<ContactListState> = sequenceOf(
        ContactListState(
            items = SyncedItemSnapshotList.initial(itemList),
            loadState = NotLoadingCombinedLoadState,
        ),
        ContactListState(loadState = LoadingCombinedLoadState),
        ContactListState(loadState = ErrorCombinedLoadState),
    )

    companion object {
        private val itemList = List(10) { ContactDomain.Sample }
        private val NotLoadingCombinedLoadState =
            combinedState(refreshState = NotLoading(endOfPaginationReached = false))
        private val LoadingCombinedLoadState =
            combinedState(refreshState = Loading)
        private val ErrorCombinedLoadState =
            combinedState(refreshState = Error(error = Throwable("Error while loading")))

        private fun combinedState(refreshState: LoadState) = CombinedLoadStates(
            refresh = refreshState,
            prepend = InitialLoadStates.prepend,
            append = InitialLoadStates.append,
            source = InitialLoadStates
        )
    }
}