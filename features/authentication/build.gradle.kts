import dependencies.implementationOfAuthentication
import dependencies.implementationOfCoroutines
import dependencies.implementationOfHilt
import dependencies.implementationOfNavigation
import dependencies.implementationOfUniFlow
import dependencies.implementationOfViewBindingDelegate

plugins {
    `android-library-convention`
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

dependencies {
    implementationOfUniFlow()
    implementationOfNavigation()
    implementationOfCoroutines()
    implementationOfHilt()
    implementationOfAuthentication()
    implementationOfViewBindingDelegate()

    implementation(project(":core-ui"))
    implementation(project(":core"))

    testImplementation("junit:junit:4.13.2")
}