package org.example.domain.contacts.mappers

import org.example.data.database.model.ContactEntity
import org.example.domain.contacts.model.ContactDomain
import org.junit.Test

class ToContactDomainTest {

    private val mapper = ToContactDomain()

    @Test
    fun testMapping() {
        val entity = ContactEntity(
            uid = 123,
            lastName = "Doe",
            firstName = "John",
            pictureUrl = "pictureUrl",
            thumbnailUrl = "thumbnailUrl"
        )
        val expectedDomain = ContactDomain(
            id = 123,
            lastName = "Doe",
            firstName = "John",
            pictureUrl = "pictureUrl",
            thumbnailUrl = "thumbnailUrl"
        )

        val actualDomain = mapper(entity)

        assert(expectedDomain == actualDomain)
    }
}