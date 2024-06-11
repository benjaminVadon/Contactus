package org.example.contactus.mainActivityContent

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import org.example.contactus.mainActivityContent.components.ContentForState

@Composable
internal fun MainActivityContent(
    viewModel: MainActivityContentViewModel = hiltViewModel(),
) {
    val state by viewModel.uiStateFlow.collectAsState()
    ContentForState(state)
}