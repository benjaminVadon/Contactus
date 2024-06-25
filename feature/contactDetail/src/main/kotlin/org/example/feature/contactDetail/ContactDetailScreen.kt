package org.example.feature.contactDetail

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
public data class ContactDetailScreen(val contactId: Int)

public fun NavGraphBuilder.contactDetail(): Unit = composable<ContactDetailScreen>(
    enterTransition = { slideInHorizontally(initialOffsetX = { it / 2 }) },
    exitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
) {
    val contactDetailViewModel: ContactDetailViewModel = hiltViewModel()
    val state by contactDetailViewModel.uiStateFlow.collectAsStateWithLifecycle()

    ContactDetailContent(state = state)
}