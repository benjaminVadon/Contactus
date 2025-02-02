plugins {
    id("org.example.android-library")
    id("org.example.compose")
}

android {
    namespace = "org.example.utils.mvi"
}

dependencies {
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.bundles.test)
}