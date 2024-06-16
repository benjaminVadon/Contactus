package org.example.domain.contacts.model

data class ContactDomain(
    val id: Int,
    val lastName: String,
    val firstName: String,
    val pictureUrl: String,
    val thumbnailUrl: String,
    val age: Int,
    val nationality: String,
    val gender: String,
    val userName: String,
    val phone: String,
    val cell: String,
    val email: String,
) {
    companion object {
        val Sample = ContactDomain(
            id = 1,
            lastName = "Doe",
            firstName = "John",
            pictureUrl = "https://picsum.photos/seed/contactus/600/600",
            thumbnailUrl = "https://picsum.photos/seed/contactus/200/200",
            age = 42,
            nationality = "US",
            gender = "Male",
            userName = "johndoe",
            phone = "1234567890",
            cell = "9876543210",
            email = "johndoe@example.com",
        )
    }
}