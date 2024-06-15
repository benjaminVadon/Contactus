package org.example.data.network.model

data class Dob(
    val age: Int,
    val date: String
) {
    companion object {
        internal val Sample = Dob(
            age = 42,
            date = "DobDate",
        )
    }
}