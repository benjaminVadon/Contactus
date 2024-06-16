package org.example.domain.contacts.mappers

import org.example.data.database.model.ContactEntity
import org.example.domain.contacts.model.ContactDomain
import javax.inject.Inject

class ToContactDomain @Inject constructor() {
    operator fun invoke(contactEntity: ContactEntity): ContactDomain = with(contactEntity) {
        ContactDomain(
            id = uid,
            lastName = lastName,
            firstName = firstName,
            pictureUrl = pictureUrl,
            thumbnailUrl = thumbnailUrl,
            age = age,
            nationality = nationality,
            gender = gender,
            userName = userName,
            phone = phone,
            cell = cell,
            email = email,
            country = country,
            city = city,
            state = state,
        )
    }
}