package org.example.data.data.mappers

import org.example.data.database.model.ContactEntity
import org.example.data.network.model.Contact
import javax.inject.Inject

class ToContactEntity @Inject constructor() {
    operator fun invoke(contact: Contact) = with(contact) {
        ContactEntity(
            uid = 0,
            firstName = name.first,
            lastName = name.last,
            pictureUrl = picture.large,
            thumbnailUrl = picture.medium,
            age = dob.age,
            nationality = nat,
            gender = gender,
            userName = login.username,
            phone = phone,
            cell = cell,
            email = email,
        )
    }
}