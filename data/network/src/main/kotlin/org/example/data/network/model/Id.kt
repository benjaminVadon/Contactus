package org.example.data.network.model

data class Id(
    val name: String,
    val value: String
) {
    companion object {
        internal val Sample = Id(
            name = "IdName",
            value = "IdValue",
        )
    }
}