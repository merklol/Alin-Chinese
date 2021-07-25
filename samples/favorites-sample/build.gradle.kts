import libraries.implementationOfHilt
import libraries.implementationOfMockk
import libraries.implementationOfNavigation
import extensions.configuration

plugins {
    `android-app-convention`
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

configuration(
    applicationId = "com.maximapps.favoritessample",
    testInstrumentationRunner = "com.maximapps.favoritessample.hilt.HiltTestRunner",
    resolutionStrategy = {
        force("androidx.test:monitor:1.4.0-rc01")
    },
    jniLibs = {
        useLegacyPackaging = true
    }
)

dependencies {
    implementation(project(Modules.Core))
    implementation(project(Modules.CoreUI))
    implementation(project(Features.Page))
    implementation(project(Features.Favorites))

    implementationOfNavigation()
    implementationOfHilt()

    implementationOfMockk()
    debugImplementation(StandardLibraries.FragmentTesting)

    androidTestImplementation(project(Modules.TestUtils))
    androidTestImplementation(StandardLibraries.AndroidXJunit)
    androidTestImplementation(StandardLibraries.EspressoCore)
    androidTestImplementation(StandardLibraries.EspressoContrib)
    androidTestImplementation(StandardLibraries.NavigationTesting)
}