package org.example.data.network.model

data class Location(
    val city: String,
    val coordinates: Coordinates,
    val country: String,
    val postcode: String,
    val state: String,
    val street: Street,
    val timezone: Timezone
) {
    companion object {
        internal val Sample = Location(
            city = "LocationCity",
            coordinates = Coordinates.Sample,
            country = "LocationCountry",
            postcode = "LocationPostcode",
            state = "LocationState",
            street = Street.Sample,
            timezone = Timezone.Sample,
        )
    }
}