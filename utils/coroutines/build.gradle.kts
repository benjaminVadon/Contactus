plugins {
    id("org.example.android-library")
    id("org.example.hilt")
}

android {
    namespace = "org.example.utils.coroutines"
}

dependencies {
    testImplementation(libs.bundles.test)
}