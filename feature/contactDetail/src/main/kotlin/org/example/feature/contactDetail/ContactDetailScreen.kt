package org.example.feature.contactDetail

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
public data class ContactDetailScreen(val contactId: Int)

public fun NavGraphBuilder.contactDetail(): Unit =
    composable<ContactDetailScreen> {
        val contactDetailViewModel: ContactDetailViewModel = hiltViewModel()
        val state by contactDetailViewModel.uiStateFlow.collectAsState()

        ContactDetailContent(state = state)
    }