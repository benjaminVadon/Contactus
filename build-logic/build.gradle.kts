import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(libs.versions.java.get().toInt())
    explicitApi()
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs += listOf(
            "-Xcontext-receivers",
        )
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    implementation(libs.semver)

    // Workaround for https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "org.example.android-application"
            implementationClass = "org.example.gradle.plugins.AndroidApplicationPlugin"
        }
        register("androidLibrary") {
            id = "org.example.android-library"
            implementationClass = "org.example.gradle.plugins.AndroidLibraryPlugin"
        }
        register("feature") {
            id = "org.example.feature"
            implementationClass = "org.example.gradle.plugins.FeaturePlugin"
        }
        register("compose") {
            id = "org.example.compose"
            implementationClass = "org.example.gradle.plugins.ComposePlugin"
        }
        register("hilt") {
            id = "org.example.hilt"
            implementationClass = "org.example.gradle.plugins.HiltPlugin"
        }
    }
}