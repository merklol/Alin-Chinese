import libraries.implementationOfHilt
import libraries.implementationOfNavigation
import libraries.implementationOfViewBindingDelegate

plugins {
    `android-library-convention`
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(project(Modules.Core))
    implementation(project(Modules.CoreUI))

    implementationOfHilt()
    implementationOfNavigation()
    implementationOfViewBindingDelegate()
}