import io.github.z4kn4fein.semver.Version
import org.example.gradle.extensions.toCode

plugins {
    id("org.example.android-application")
    id("org.example.hilt")
}


android {
    namespace = "org.example.contactus"
    val appVersion = Version(0, 0, 1)
    defaultConfig {
        versionCode = appVersion.toCode()
        versionName = appVersion.toString()
        resourceConfigurations += sequenceOf("en")
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.findByName("debug")
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}
dependencies {
    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso)

    implementation(libs.bundles.androidx.compose)
    implementation(libs.bundles.androidx.navigation)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.splash.screen)
    implementation(libs.hilt.navigation.compose)

    implementation(projects.domain.connectivity)
    implementation(projects.feature.contactList)
    implementation(projects.utils.coroutines)
    implementation(projects.utils.designSystem)
    implementation(projects.utils.mvi)
    implementation(projects.utils.sharedResources)
}