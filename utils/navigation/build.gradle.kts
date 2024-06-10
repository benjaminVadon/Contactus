plugins {
    id("org.example.android-library")
}

android {
    namespace = "org.example.utils.navigation"
}

dependencies {
    implementation(libs.androidx.navigation.compose)
    implementation(projects.utils.mvi)
    testImplementation(libs.bundles.test)
}