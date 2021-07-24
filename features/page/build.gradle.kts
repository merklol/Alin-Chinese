import dependencies.implementationOfCoil
import dependencies.implementationOfHilt
import dependencies.implementationOfGson
import dependencies.implementationOfNavigation
import dependencies.androidTestImplementationOfRoom
import dependencies.implementationOfUniFlow
import dependencies.implementationOfViewBindingDelegate

plugins {
    `android-library-convention`
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

dependencies {
    implementationOfCoil()
    implementationOfGson()
    implementationOfUniFlow()
    implementationOfHilt()
    implementationOfNavigation()
    implementationOfViewBindingDelegate()

    implementation(project(":core-ui"))
    implementation(project(":core"))

    androidTestImplementationOfRoom()
    testImplementation("junit:junit:4.13.2")
}