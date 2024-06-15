package org.example.data.network.model

data class ContactsResponse(
    val info: Info,
    val results: List<Contact>
) {
    companion object {
        val Sample = ContactsResponse(
            info = Info.Sample,
            results = listOf(Contact.Sample, Contact.Sample)
        )
    }
}