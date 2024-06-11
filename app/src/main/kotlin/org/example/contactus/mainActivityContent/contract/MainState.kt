package org.example.contactus.mainActivityContent.contract

import org.example.utils.mvi.contract.State

data class MainState(
    val showInternetConnectionIndicator: Boolean = false,
) : State {
    internal fun onInternetConnectionChanged(isConnected: Boolean): MainState = copy(
        showInternetConnectionIndicator = isConnected.not()
    )
}