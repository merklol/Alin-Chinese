import libraries.implementationOfCoil
import libraries.implementationOfHilt
import libraries.implementationOfGson
import libraries.implementationOfNavigation
import libraries.androidTestImplementationOfRoom
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

    implementationOfCoil()
    implementationOfGson()
    implementationOfUniFlow()
    implementationOfHilt()
    implementationOfNavigation()
    implementationOfViewBindingDelegate()

    testImplementation(StandardLibraries.JUnit)
    androidTestImplementationOfRoom()
}