package org.example.feature.contactList.contract

import org.example.utils.mvi.contract.MVIEvent

internal sealed interface ContactListEvents : MVIEvent {
    @JvmInline
    value class OnContactClick(val contactId: Int) : ContactListEvents
}