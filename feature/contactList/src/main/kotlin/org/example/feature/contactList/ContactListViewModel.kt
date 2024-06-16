package org.example.feature.contactList

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn
import org.example.domain.contacts.GetContactsUseCase
import org.example.domain.contacts.model.ContactDomain
import org.example.feature.contactList.contract.ContactListActions
import org.example.feature.contactList.contract.ContactListActions.GoToContactDetails
import org.example.feature.contactList.contract.ContactListEvents
import org.example.feature.contactList.contract.ContactListState
import org.example.utils.coroutines.dispatchers.AppDispatchers.IO
import org.example.utils.coroutines.dispatchers.Dispatcher
import org.example.utils.mvi.MVI
import org.example.utils.pagingItems.LazyPagingItems
import javax.inject.Inject

@HiltViewModel
internal class ContactListViewModel @Inject constructor(
    getContactsUseCase: GetContactsUseCase,
    @Dispatcher(IO) private val dispatcher: CoroutineDispatcher,
) : MVI<ContactListState, ContactListEvents, ContactListActions>(ContactListState()) {

    private val contactsFlow: Flow<PagingData<ContactDomain>> = getContactsUseCase()
        .flowOn(Dispatchers.IO)
        .cachedIn(viewModelScope)

    private val lazyPagingItems: LazyPagingItems<ContactDomain> = LazyPagingItems(contactsFlow)

    init {
        lazyPagingItems.observeContacts()
    }

    override fun process(event: ContactListEvents) {
        when (event) {
            is ContactListEvents.OnContactClick -> onContactClick(event.contactId)
        }
    }

    private fun LazyPagingItems<ContactDomain>.observeContacts() {
        launch(dispatcher) {
            collectPagingData()
        }
        launch(dispatcher) {
            itemSnapshotListFlow.collectUpdateState { updateItems(it) }
        }
        launch(dispatcher) {
            loadStateFlow.filterNotNull().collectUpdateState { updateLoadState(it) }
        }
    }

    private fun onContactClick(contactId: Int) =
        emit(dispatcher, GoToContactDetails(contactId))
}