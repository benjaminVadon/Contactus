plugins {
    id("org.example.android-library")
    id("org.example.hilt")
}

android {
    namespace = "org.example.data.network"
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.gson)
    implementation(libs.okhttp.logger)
    implementation(projects.domain.connectivity)
    implementation(projects.utils.resultAdapter)
    testImplementation(libs.bundles.test)
    testImplementation(libs.okhttp.mockwebserver)
}