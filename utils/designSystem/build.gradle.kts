plugins {
    id("org.example.android-library")
    id("org.example.compose")
}

android {
    namespace = "org.example.utils.designSystem"
}

dependencies {
    implementation(libs.bundles.androidx.compose)
    implementation(libs.androidx.compose.ui.text.google.fonts)
}