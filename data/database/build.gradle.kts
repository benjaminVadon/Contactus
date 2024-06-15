plugins {
    id("org.example.android-library")
    id("org.example.hilt")
}

android {
    namespace = "org.example.data.database"
}

dependencies {
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)
}