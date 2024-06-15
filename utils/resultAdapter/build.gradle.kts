plugins {
    id("org.example.android-library")
    id("org.example.hilt")
}

android {
    namespace = "org.example.utils.resultAdapter"
}

dependencies {
    implementation(libs.retrofit)
    testImplementation(libs.bundles.test)
    testImplementation(libs.kotlin.reflect)
}