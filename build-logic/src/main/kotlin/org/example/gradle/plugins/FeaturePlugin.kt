package org.example.gradle.plugins

import org.example.gradle.utils.implementation
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

public class FeaturePlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        val libs = the<LibrariesForLibs>()

        pluginManager.apply {
            apply("org.example.android-library")
            apply("org.example.hilt")
            apply("org.example.compose")
        }
        configure<KotlinProjectExtension> {
            explicitApi()
        }

        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                explicitApiMode = ExplicitApiMode.Strict
            }
        }

        dependencies {
            implementation(libs.bundles.androidx.compose)
            implementation(libs.bundles.androidx.navigation)
            implementation(libs.hilt.navigation.compose)
            implementation(project(":utils:coroutines"))
            implementation(project(":utils:mvi"))
            implementation(project(":utils:navigation"))
        }
    }
}