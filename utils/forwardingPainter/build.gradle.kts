plugins {
    id("org.example.android-library")
    id("org.example.compose")
}

android {
    namespace = "org.example.utils.forwardingPainter"
}

dependencies {
    implementation(libs.bundles.androidx.compose)
}