package org.example.feature.contactList.contract

import org.example.utils.mvi.contract.MVIAction

internal sealed interface ContactListActions : MVIAction {
    @JvmInline
    value class GoToContactDetails(val contactId: Int) : ContactListActions
}
