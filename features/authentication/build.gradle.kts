import libraries.implementationOfAuthentication
import libraries.implementationOfCoroutines
import libraries.implementationOfHilt
import libraries.implementationOfNavigation
import libraries.implementationOfUniFlow
import libraries.implementationOfViewBindingDelegate

plugins {
    `android-library-convention`
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

dependencies {
    implementation(project(Modules.Core))
    implementation(project(Modules.CoreUI))

    implementationOfHilt()
    implementationOfUniFlow()
    implementationOfNavigation()
    implementationOfCoroutines()
    implementationOfAuthentication()
    implementationOfViewBindingDelegate()

    testImplementation(StandardLibraries.JUnit)
}