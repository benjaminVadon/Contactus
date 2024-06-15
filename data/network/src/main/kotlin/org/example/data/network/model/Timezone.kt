package org.example.data.network.model

data class Timezone(
    val description: String,
    val offset: String
) {
    companion object {
        internal val Sample = Timezone(
            description = "TimezoneDescription",
            offset = "TimezoneOffset",
        )
    }
}