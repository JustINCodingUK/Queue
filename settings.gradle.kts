pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Queue"
include(":app")
include(":core:network")
include(":core:model")
include(":core:data")
include(":core:ui")
include(":core:ext")
include(":features:posts")
//include(":core:database")
include(":features:details")
