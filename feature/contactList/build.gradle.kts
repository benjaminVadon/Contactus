plugins {
    id("org.example.feature")
    id("kotlinx-serialization")
}

android {
    namespace = "org.example.feature.contactList"
}
dependencies {
    implementation(libs.kotlinx.serialization)
}