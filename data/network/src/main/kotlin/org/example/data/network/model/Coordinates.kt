package org.example.data.network.model

data class Coordinates(
    val latitude: String,
    val longitude: String
) {
    companion object {
        internal val Sample = Coordinates(
            latitude = "CoordinatesLatitude",
            longitude = "CoordinatesLongitude",
        )
    }
}