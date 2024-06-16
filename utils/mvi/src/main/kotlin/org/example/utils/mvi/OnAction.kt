package org.example.utils.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.collectLatest
import org.example.utils.mvi.contract.MVIAction
import org.example.utils.mvi.contract.MVIEvent
import org.example.utils.mvi.contract.MVIState

@Composable
fun <VM, S, E, A> OnAction(
    viewModel: VM,
    collector: A.() -> Unit
) where VM : MVI<out S, out E, out A>, S : MVIState, E : MVIEvent, A : MVIAction =
    LaunchedEffect(viewModel) {
        viewModel.actionFlow.collectLatest {
            it.collector()
        }
    }