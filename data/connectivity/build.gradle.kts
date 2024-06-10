plugins {
    id("org.example.android-library")
    id("org.example.hilt")
}

android {
    namespace = "org.example.data.connectivity"
}

dependencies {
    implementation(projects.utils.coroutines)
    testImplementation(libs.bundles.test)
}