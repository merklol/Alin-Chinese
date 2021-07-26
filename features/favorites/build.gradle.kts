import libraries.implementationOfHilt
import libraries.androidTestImplementationOfRoom
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
    implementation(project(Features.Page))

    implementationOfHilt()
    implementationOfUniFlow()
    implementationOfNavigation()
    implementationOfViewBindingDelegate()

    testImplementation(StandardLibraries.JUnit)
    androidTestImplementationOfRoom()
}