plugins {
    id("org.example.feature")
    id("kotlinx-serialization")
}

android {
    namespace = "org.example.feature.contactList"
}

dependencies {
    implementation(libs.kotlinx.serialization)
    implementation(libs.androidx.paging.compose)
    implementation(libs.coil.compose)
    implementation(projects.domain.contacts)
    implementation(projects.utils.coroutines)
    implementation(projects.utils.designSystem)
    implementation(projects.utils.forwardingPainter)
    implementation(projects.utils.pagingItems)
    implementation(projects.utils.sharedResources)
}