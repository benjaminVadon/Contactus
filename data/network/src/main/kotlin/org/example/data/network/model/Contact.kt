package org.example.data.network.model

data class Contact(
    val cell: String,
    val dob: Dob,
    val email: String,
    val gender: String,
    val id: Id,
    val location: Location,
    val login: Login,
    val name: Name,
    val nat: String,
    val phone: String,
    val picture: Picture,
    val registered: Registered
) {
    companion object {
        val Sample = Contact(
            cell = "ContactCell",
            dob = Dob.Sample,
            email = "ContactEmail",
            gender = "ContactGender",
            id = Id.Sample,
            location = Location.Sample,
            login = Login.Sample,
            name = Name.Sample,
            nat = "ContactNat",
            phone = "ContactPhone",
            picture = Picture.Sample,
            registered = Registered.Sample,
        )
    }
}