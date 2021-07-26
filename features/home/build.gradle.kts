import libraries.implementationOfCoil
import libraries.implementationOfCoroutines
import libraries.implementationOfHilt
import libraries.implementationOfFirestore
import libraries.implementationOfLifecycle
import libraries.implementationOfNavigation
import libraries.implementationOfPaging3
import libraries.androidTestImplementationOfRoom
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
    implementationOfCoil()
    implementationOfPaging3()
    implementationOfFirestore()
    implementationOfLifecycle()
    implementationOfCoroutines()
    implementationOfNavigation()
    implementationOfViewBindingDelegate()

    testImplementation(StandardLibraries.JUnit)
    androidTestImplementationOfRoom()
}