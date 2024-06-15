package org.example.domain.contacts

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.example.data.data.ContactsRepository
import org.example.data.database.model.ContactEntity
import org.example.domain.contacts.mappers.ToContactDomain
import org.example.domain.contacts.model.ContactDomain
import org.junit.Test

class GetContactsUseCaseTest {

    private val contactsRepository = mockk<ContactsRepository>()
    private val toContactDomain = ToContactDomain()
    private val useCase = GetContactsUseCase(contactsRepository, toContactDomain)

    @Test
    fun testGetContacts() = runTest {
        val contactEntity = ContactEntity(
            uid = 1,
            lastName = "Doe",
            firstName = "John",
            pictureUrl = "pictureUrl",
            thumbnailUrl = "thumbnailUrl"
        )
        val pagingDataEntity = PagingData.from(listOf(contactEntity))
        val expectedContactDomain = ContactDomain(
            id = 1,
            lastName = "Doe",
            firstName = "John",
            pictureUrl = "pictureUrl",
            thumbnailUrl = "thumbnailUrl"
        )

        coEvery { contactsRepository.contacts } returns flowOf(pagingDataEntity)

        val contacts: List<ContactDomain> = useCase().asSnapshot()
        assert(contacts.contains(expectedContactDomain))
    }

}
