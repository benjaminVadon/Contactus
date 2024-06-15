pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    includeBuild("build-logic")
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")


rootProject.name = "Contactus"

include(":app")

include(":data:connectivity")
include(":data:network")

include(":domain:connectivity")

include(":feature:contactList")

include(":utils:coroutines")
include(":utils:designSystem")
include(":utils:mvi")
include(":utils:sharedResources")