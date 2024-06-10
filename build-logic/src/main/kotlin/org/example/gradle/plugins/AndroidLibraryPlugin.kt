package org.example.gradle.plugins

import com.android.build.gradle.LibraryExtension
import org.example.gradle.utils.apply
import org.example.gradle.utils.configureAndroid
import org.example.gradle.utils.configureKotlinAndroid
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.the

public class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        val libs = the<LibrariesForLibs>()

        pluginManager.apply(
            libs.plugins.android.library,
            libs.plugins.kotlin.android,
        )

        configureKotlinAndroid(libs)

        configure<LibraryExtension> {
            configureAndroid(libs)
            if (file("consumer-rules.pro").exists()) {
                buildTypes {
                    forEach {
                        it.consumerProguardFiles("consumer-rules.pro")
                    }
                }
            }
        }
    }
}
