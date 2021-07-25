import libraries.implementationOfHilt
import libraries.implementationOfFirestore
import libraries.implementationOfNavigation
import libraries.implementationOfPaging3
import libraries.implementationOfViewBindingDelegate

plugins {
    `android-library-convention`
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(project(Modules.Core))
    implementation(project(Modules.CoreUI))
    implementation(project(Features.Home))
    implementation(project(Features.Profile))
    implementation(project(Features.Favorites))

    implementationOfPaging3()
    implementationOfFirestore()
    implementationOfHilt()
    implementationOfNavigation()
    implementationOfViewBindingDelegate()

    testImplementation(StandardLibraries.JUnit)
    androidTestImplementation(StandardLibraries.AndroidXJunit)
    androidTestImplementation(StandardLibraries.EspressoCore)
}