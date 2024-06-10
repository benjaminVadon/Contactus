package org.example.gradle.utils

import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.AppliedPlugin
import org.gradle.api.plugins.PluginManager
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.the
import org.gradle.plugin.use.PluginDependency
import java.util.Properties

internal fun DependencyHandler.implementation(
    vararg dependencyNotations: Any,
) {
    dependencyNotations.forEach {
        add("implementation", it)
    }
}

internal fun DependencyHandler.androidTestImplementation(
    vararg dependencyNotations: Any,
) {
    dependencyNotations.forEach {
        add("androidTestImplementation", it)
    }
}

internal fun DependencyHandler.ksp(
    vararg dependencyNotations: Any,
) {
    dependencyNotations.forEach {
        add("ksp", it)
    }
}

internal fun PluginManager.apply(vararg plugins: Provider<PluginDependency>) {
    plugins.forEach { plugin ->
        apply(plugin.get().pluginId)
    }
}

internal fun PluginManager.withPlugin(
    plugin: Provider<PluginDependency>,
    action: AppliedPlugin.() -> Unit,
) {
    withPlugin(plugin.get().pluginId, action)
}

internal fun PluginManager.withPlugins(
    first: Provider<PluginDependency>,
    vararg others: Provider<PluginDependency>,
    action: (AppliedPlugin) -> Unit,
) {
    withPlugin(first, action)
    others.forEach { plugin ->
        withPlugin(plugin, action)
    }
}

public fun Project.getLocalOrEnvProperty(
    key: String
): String? {
    val properties = Properties()
    val localPropertiesFile = project.file("local.properties")
    if (localPropertiesFile.exists()) {
        localPropertiesFile.inputStream().use { properties.load(it) }
    }
    return if (properties.hasProperty(key)) {
        properties.getProperty(key)
    } else {
        System.getenv(key)
    }
}

public fun Properties.hasProperty(
    key: String,
    suffix: String? = null,
): Boolean = this.getProperty(key.suffix(suffix)) != null

public fun String.suffix(
    suf: String?
): String =
    if (suf == null) this
    else "${this}.$suf"
