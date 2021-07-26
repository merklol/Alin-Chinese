pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "com.android.library")
                useModule("com.android.tools.build:gradle:${requested.version}")

            if (requested.id.id == "com.android.application")
                useModule("com.android.tools.build:gradle:${requested.version}")

            if (requested.id.id == "com.google.gms.google-services")
                useModule("com.google.gms:google-services:${requested.version}")

            if (requested.id.id == "dagger.hilt.android.plugin")
                useModule(
                    "com.google.dagger:hilt-android-gradle-plugin:${
                        requested.version
                    }"
                )

            if (requested.id.id == "androidx.navigation.safeargs")
                useModule(
                    "androidx.navigation:navigation-safe-args-gradle-plugin:${
                        requested.version
                    }"
                )
        }
    }
}
rootProject.name = "Alin Chinese"

include(":app")
include(":main")
include(":core")
include(":core-ui")
include(":test-utils")
include(":features:home")
include(":features:page")
include(":features:profile")
include(":features:favorites")
include(":features:authentication")

include(":samples:auth-sample")
include(":samples:home-sample")
include(":samples:profile-sample")
include(":samples:favorites-sample")
