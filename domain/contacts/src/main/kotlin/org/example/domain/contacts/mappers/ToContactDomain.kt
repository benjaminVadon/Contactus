package org.example.domain.contacts.mappers

import org.example.data.database.model.ContactEntity
import org.example.domain.contacts.model.ContactDomain
import javax.inject.Inject

class ToContactDomain @Inject constructor() {
    operator fun invoke(contactEntity: ContactEntity): ContactDomain = with(contactEntity) {
        ContactDomain(
            id = uid,
            firstName = firstName,
            lastName = lastName,
            pictureUrl = pictureUrl,
            thumbnailUrl = thumbnailUrl,
        )
    }
}