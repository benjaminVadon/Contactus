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
include(":data:data")
include(":data:database")
include(":data:network")

include(":domain:connectivity")
include(":domain:contacts")

include(":feature:contactList")
include(":feature:contactDetail")

include(":utils:coroutines")
include(":utils:designSystem")
include(":utils:forwardingPainter")
include(":utils:mvi")
include(":utils:pagingItems")
include(":utils:resultAdapter")
include(":utils:sharedResources")