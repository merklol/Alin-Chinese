import dependencies.implementAuthentication
import dependencies.implementCoroutines
import dependencies.implementDaggerHilt
import dependencies.implementNavigation
import dependencies.implementUniFlow
import dependencies.implementViewBindingDelegate

plugins {
    `android-library-convention`
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

dependencies {
    implementUniFlow()
    implementNavigation()
    implementCoroutines()
    implementDaggerHilt()
    implementAuthentication()
    implementViewBindingDelegate()

    implementation(project(":core-ui"))
    implementation(project(":core"))

    testImplementation("junit:junit:4.13.2")
}