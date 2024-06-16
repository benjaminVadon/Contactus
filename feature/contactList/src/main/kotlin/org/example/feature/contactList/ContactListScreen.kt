package org.example.feature.contactList

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.example.feature.contactList.contract.ContactListActions.GoToContactDetails
import org.example.utils.mvi.OnAction

@Serializable
public object ContactListScreen

public fun NavGraphBuilder.contactList(
    navigation: ContactListNavigation
): Unit = composable<ContactListScreen> {
    val contactListViewModel: ContactListViewModel = hiltViewModel()
    val state by contactListViewModel.uiStateFlow.collectAsState()

    OnAction(viewModel = contactListViewModel) {
        when (this) {
            is GoToContactDetails -> navigation.goToContactDetails(contactId)
        }
    }
    ContactListContent(
        state = state,
        processEvent = contactListViewModel::process,
    )
}

public data class ContactListNavigation(
    val goToContactDetails: (userId: Int) -> Unit
)