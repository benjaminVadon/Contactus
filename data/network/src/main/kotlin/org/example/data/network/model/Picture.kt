package org.example.data.network.model

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
) {
    companion object {
        internal val Sample = Picture(
            large = "PictureLarge",
            medium = "PictureMedium",
            thumbnail = "PictureThumbnail",
        )
    }
}