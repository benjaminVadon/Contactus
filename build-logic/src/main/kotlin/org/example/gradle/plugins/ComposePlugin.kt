package org.example.gradle.plugins

import com.android.build.gradle.BaseExtension
import org.example.gradle.utils.androidTestImplementation
import org.example.gradle.utils.implementation
import org.example.gradle.utils.withPlugins
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.File

public class ComposePlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        val libs = the<LibrariesForLibs>()

        pluginManager.withPlugins(
            libs.plugins.android.application,
            libs.plugins.android.library,
        ) {
            configureCompose(libs)
        }
    }
}

@Suppress("UnstableApiUsage")
private fun Project.configureCompose(libs: LibrariesForLibs) {
    configure<BaseExtension> {
        buildFeatures.compose = true

        composeOptions {
            kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
        }
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            freeCompilerArgs += buildComposeMetricsParameters()
        }
    }

    dependencies {
        implementation(platform(libs.androidx.compose.bom))

        androidTestImplementation(platform(libs.androidx.compose.bom))
    }
}

private fun Project.buildComposeMetricsParameters(): List<String> {
    return buildList {
        val enableMetricsProvider = providers.gradleProperty("enableComposeCompilerMetrics")
        val enableMetrics = enableMetricsProvider.orNull == "true"
        if (enableMetrics) {
            val metricsFolder = File(layout.buildDirectory.get().asFile, "compose-metrics")
            add("-P")
            add(
                "plugin" +
                        ":androidx.compose.compiler.plugins.kotlin" +
                        ":metricsDestination=${metricsFolder.absolutePath}",
            )
        }

        val enableReportsProvider = providers.gradleProperty("enableComposeCompilerReports")
        val enableReports = enableReportsProvider.orNull == "true"
        if (enableReports) {
            val reportsFolder = File(layout.buildDirectory.get().asFile, "compose-reports")
            add("-P")
            add(
                "plugin" +
                        ":androidx.compose.compiler.plugins.kotlin" +
                        ":reportsDestination=${reportsFolder.absolutePath}",
            )
        }
    }
}
