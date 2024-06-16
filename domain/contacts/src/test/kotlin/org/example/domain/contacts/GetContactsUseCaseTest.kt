package org.example.domain.contacts

import android.util.Log
import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
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
            firstName = "John",
            lastName = "Doe",
            pictureUrl = "pictureUrl",
            thumbnailUrl = "thumbnailUrl",
            age = 20,
            gender = "Male",
            nationality = "American",
            userName = "johndoe",
            phone = "1234567890",
            cell = "9876543210",
            email = "johndoe@example.com",
            country = "US",
            city = "New York",
            state = "New York",
        )
        val pagingDataEntity = PagingData.from(listOf(contactEntity))
        val expectedContactDomain = ContactDomain(
            id = 1,
            lastName = "Doe",
            firstName = "John",
            pictureUrl = "pictureUrl",
            thumbnailUrl = "thumbnailUrl",
            age = 20,
            gender = "Male",
            nationality = "American",
            userName = "johndoe",
            phone = "1234567890",
            cell = "9876543210",
            email = "johndoe@example.com",
            country = "US",
            city = "New York",
            state = "New York",
        )

        mockkStatic(Log::class)
        every { Log.isLoggable(any(), any()) } returns true
        every { Log.v(any(), any(), any()) } returns 0
        every { Log.d(any(), any(), any()) } returns 0
        coEvery { contactsRepository.contacts } returns flowOf(pagingDataEntity)

        val contacts: List<ContactDomain> = useCase().asSnapshot()
        assert(contacts.contains(expectedContactDomain))
    }

}
