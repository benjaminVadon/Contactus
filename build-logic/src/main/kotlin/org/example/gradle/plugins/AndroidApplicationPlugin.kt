package org.example.gradle.plugins

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.example.gradle.utils.apply
import org.example.gradle.utils.configureAndroid
import org.example.gradle.utils.configureKotlinAndroid
import org.example.gradle.utils.implementation
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the

public class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        val libs = the<LibrariesForLibs>()

        pluginManager.apply(
            libs.plugins.android.application,
            libs.plugins.kotlin.android
        )
        pluginManager.apply {
            apply("org.example.compose")
            apply("org.example.hilt")
        }

        configureKotlinAndroid(libs)

        configure<BaseAppModuleExtension> {
            configureAndroid(libs)
            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }

            buildTypes {
                named("release") {
                    isShrinkResources = true
                    isMinifyEnabled = true
                    isDebuggable = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro",
                    )
                }
            }
        }
    }
}