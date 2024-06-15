package org.example.data.network.model

data class Street(
    val name: String,
    val number: Int
) {
    companion object {
        internal val Sample = Street(
            name = "StreetName",
            number = 42,
        )
    }
}