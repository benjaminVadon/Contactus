package org.example.data.network.model

data class Info(
    val page: Int,
    val results: Int,
    val seed: String,
    val version: String
) {
    companion object {
        internal val Sample = Info(
            page = 42,
            results = 42,
            seed = "InfoSeed",
            version = "InfoVersion"
        )
    }
}