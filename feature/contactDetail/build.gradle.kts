plugins {
    id("org.example.feature")
    id("kotlinx-serialization")
}

android {
    namespace = "org.example.feature.contactDetail"
}

dependencies {
    implementation(libs.kotlinx.serialization)
    implementation(libs.coil.compose)
    implementation(projects.domain.contacts)
    implementation(projects.utils.designSystem)
    implementation(projects.utils.forwardingPainter)
    implementation(projects.utils.sharedResources)
}