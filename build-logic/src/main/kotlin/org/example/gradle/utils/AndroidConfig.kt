package org.example.gradle.utils

import com.android.build.gradle.BaseExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

context(Project)
internal fun BaseExtension.configureAndroid(libs: LibrariesForLibs) {
    compileSdkVersion(libs.versions.android.sdk.compile.get().toInt())

    defaultConfig.apply {
        minSdk = libs.versions.android.sdk.min.get().toInt()
        targetSdk = libs.versions.android.sdk.target.get().toInt()
    }
}
