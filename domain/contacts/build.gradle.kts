plugins {
    id("org.example.android-library")
    id("org.example.hilt")
}

android {
    namespace = "org.example.domain.contacts"
}

dependencies {
    implementation(libs.androidx.paging.compose)
    implementation(projects.data.data)
    implementation(projects.data.database)
    testImplementation(libs.bundles.test)
    testImplementation(libs.androidx.paging.testing)
}