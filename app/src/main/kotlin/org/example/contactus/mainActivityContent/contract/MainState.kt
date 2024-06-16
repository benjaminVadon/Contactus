package org.example.contactus.mainActivityContent.contract

import org.example.utils.mvi.contract.MVIState

data class MainState(
    val showInternetConnectionIndicator: Boolean = false,
) : MVIState {
    internal fun onInternetConnectionChanged(isConnected: Boolean): MainState = copy(
        showInternetConnectionIndicator = isConnected.not()
    )
}