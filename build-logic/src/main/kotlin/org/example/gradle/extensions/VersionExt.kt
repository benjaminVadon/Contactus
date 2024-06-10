package org.example.gradle.extensions

import io.github.z4kn4fein.semver.Version

public fun Version.toCode(): Int {
    return major * 1000000 + minor * 1000 + patch
}