package org.example.data.data.mappers

import org.example.data.network.model.Contact
import org.junit.Assert.assertEquals
import org.junit.Test

class ToContactEntityTest {

    private val mapper = ToContactEntity()

    @Test
    fun `maps Contact to ContactEntity correctly`() {
        val contact = Contact.Sample
        val contactEntity = mapper(contact)

        assertEquals(0, contactEntity.uid)
        assertEquals("NameLast", contactEntity.lastName)
        assertEquals("NameFirst", contactEntity.firstName)
        assertEquals("PictureLarge", contactEntity.pictureUrl)
        assertEquals("PictureMedium", contactEntity.thumbnailUrl)
    }
}