import dependencies.implementationOfCoil
import dependencies.implementationOfCoroutines
import dependencies.implementationOfHilt
import dependencies.implementationOfFirestore
import dependencies.implementationOfLifecycle
import dependencies.implementationOfNavigation
import dependencies.implementationOfPaging3
import dependencies.androidTestImplementationOfRoom
import dependencies.implementationOfViewBindingDelegate

plugins {
    `android-library-convention`
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

dependencies {
    implementation(project(":core-ui"))
    implementation(project(":core"))
    implementation(project(":features:page"))

    implementationOfCoil()
    implementationOfPaging3()
    implementationOfFirestore()
    implementationOfLifecycle()
    implementationOfCoroutines()
    implementationOfHilt()
    implementationOfNavigation()
    implementationOfViewBindingDelegate()

    androidTestImplementationOfRoom()
    testImplementation("junit:junit:4.13.2")
}