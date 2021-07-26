import libraries.implementationOfHilt
import libraries.implementationOfNavigation
import extensions.configuration

plugins {
    `android-app-convention`
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

configuration(
    applicationId = "com.maximapps.profilesample",
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner",
)

dependencies {
    implementation(project(Modules.Core))
    implementation(project(Modules.CoreUI))
    implementation(project(Features.Profile))

    implementationOfHilt()
    implementationOfNavigation()
}