package org.example.domain.contacts

import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.data.data.ContactsRepository
import org.example.domain.contacts.mappers.ToContactDomain
import org.example.domain.contacts.model.ContactDomain
import javax.inject.Inject

class GetContactsUseCase @Inject constructor(
    private val contactsRepository: ContactsRepository,
    private val toContactDomain: ToContactDomain,
) {
    operator fun invoke(): Flow<PagingData<ContactDomain>> =
        contactsRepository.contacts.map { it.map(toContactDomain::invoke) }
}