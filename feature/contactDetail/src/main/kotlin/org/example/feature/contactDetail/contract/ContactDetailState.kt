package org.example.feature.contactDetail.contract

import org.example.domain.contacts.model.ContactDomain
import org.example.utils.mvi.contract.MVIState

internal data class ContactDetailState(
    val contact: ContactDomain? = null,
) : MVIState {

    fun setContact(contact: ContactDomain): ContactDetailState = copy(
        contact = contact,
    )
}
