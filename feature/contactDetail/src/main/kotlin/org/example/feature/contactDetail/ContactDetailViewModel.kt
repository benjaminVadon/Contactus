package org.example.feature.contactDetail

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import org.example.domain.contacts.GetContactByIdUseCase
import org.example.feature.contactDetail.contract.ContactDetailActions
import org.example.feature.contactDetail.contract.ContactDetailEvents
import org.example.feature.contactDetail.contract.ContactDetailState
import org.example.utils.coroutines.dispatchers.AppDispatchers.IO
import org.example.utils.coroutines.dispatchers.Dispatcher
import org.example.utils.mvi.MVI
import javax.inject.Inject

@HiltViewModel
internal class ContactDetailViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val getContactByIdUseCase: GetContactByIdUseCase,
    @Dispatcher(IO) private val dispatcher: CoroutineDispatcher
) : MVI<ContactDetailState, ContactDetailEvents, ContactDetailActions>(ContactDetailState()) {
    override fun process(event: ContactDetailEvents) {}

    init {
        retrieveContact(stateHandle.toRoute<ContactDetailScreen>().contactId)

    }

    private fun retrieveContact(contactId: Int) {
        launch(dispatcher = dispatcher) {
            getContactByIdUseCase(contactId)?.let { updateUiState { setContact(it) } }

        }
    }
}