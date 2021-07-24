import dependencies.implementationOfHilt
import dependencies.implementationOfNavigation
import dependencies.implementationOfViewBindingDelegate

plugins {
    `android-library-convention`
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementationOfNavigation()
    implementationOfHilt()
    implementationOfViewBindingDelegate()

    implementation(project(":core-ui"))
    implementation(project(":core"))
}