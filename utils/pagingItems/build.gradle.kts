plugins {
    id("org.example.android-library")
}

android {
    namespace = "org.example.utils.pagingItems"
}
dependencies {
    implementation(libs.androidx.paging.compose)
    testImplementation(libs.bundles.test)
}