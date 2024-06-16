package org.example.domain.contacts

import org.example.data.data.ContactsRepository
import org.example.domain.contacts.mappers.ToContactDomain
import org.example.domain.contacts.model.ContactDomain
import javax.inject.Inject

class GetContactByIdUseCase @Inject constructor(
    private val contactsRepository: ContactsRepository,
    private val toContactDomain: ToContactDomain,
) {
    suspend operator fun invoke(contactId: Int): ContactDomain? =
        contactsRepository.getContactById(contactId)?.let(toContactDomain::invoke)
}