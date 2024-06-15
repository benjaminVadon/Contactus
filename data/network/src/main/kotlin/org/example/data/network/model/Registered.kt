package org.example.data.network.model

data class Registered(
    val age: Int,
    val date: String
) {
    companion object {
        internal val Sample = Registered(
            age = 42,
            date = "RegisteredDate"
        )
    }
}