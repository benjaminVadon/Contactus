package org.example.feature.contactList

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
public object ContactListScreen

public fun NavGraphBuilder.contactList(
    navigation: ContactListNavigation
): Unit = composable<ContactListScreen> {
    val contactListViewModel: ContactListViewModel = hiltViewModel()
    val state by contactListViewModel.uiStateFlow.collectAsState()
    ContactListContent(state, contactListViewModel::process, navigation)
}


public class ContactListNavigation