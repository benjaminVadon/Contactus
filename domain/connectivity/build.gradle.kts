plugins {
    id("org.example.android-library")
    id("org.example.hilt")
}

android {
    namespace = "org.example.domain.connectivity"
}

dependencies {
    implementation(projects.data.connectivity)
    implementation(projects.utils.coroutines)
    testImplementation(libs.bundles.test)
    testImplementation(libs.kotlinx.date)
}