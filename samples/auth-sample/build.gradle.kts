import libraries.implementationOfAuthentication
import libraries.implementationOfHilt
import libraries.implementationOfMockk
import libraries.implementationOfNavigation
import extensions.configuration

plugins {
    `android-app-convention`
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.gms.google-services")
}

configuration(
    applicationId = "com.maximapps.authsample",
    testInstrumentationRunner = "com.maximapps.authsample.hilt.HiltTestRunner",
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
    implementation(project(Features.Authentication))

    implementationOfAuthentication()
    implementationOfHilt()
    implementationOfNavigation()

    implementationOfMockk()
    debugImplementation(StandardLibraries.FragmentTesting)

    androidTestImplementation(project(Modules.TestUtils))
    androidTestImplementation(StandardLibraries.EspressoCore)
    androidTestImplementation(StandardLibraries.AndroidXJunit)
    androidTestImplementation(StandardLibraries.NavigationTesting)
}