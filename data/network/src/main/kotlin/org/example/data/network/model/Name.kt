package org.example.data.network.model

data class Name(
    val first: String,
    val last: String,
    val title: String
) {
    companion object {
        internal val Sample = Name(
            first = "NameFirst",
            last = "NameLast",
            title = "NameTitle",
        )
    }
}