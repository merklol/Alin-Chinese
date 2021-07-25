import libraries.implementationOfHilt
import libraries.implementationOfFirestore
import libraries.implementationOfMockk
import libraries.implementationOfNavigation
import libraries.implementationOfPaging3
import libraries.implementationOfRoom
import libraries.androidTestImplementationOfRoom
import extensions.configuration

plugins {
    `android-app-convention`
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
}

configuration(
    applicationId = "com.maximapps.homesample",
    testInstrumentationRunner = "com.maximapps.homesample.hilt.HiltTestRunner",
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
    implementation(project(Features.Home))
    implementation(project(Features.Page))

    implementationOfRoom()
    implementationOfPaging3()
    implementationOfFirestore()
    implementationOfHilt()
    implementationOfNavigation()

    implementationOfMockk()
    debugImplementation(StandardLibraries.FragmentTesting)

    androidTestImplementationOfRoom()
    androidTestImplementation(project(Modules.TestUtils))
    androidTestImplementation(StandardLibraries.AndroidXJunit)
    androidTestImplementation(StandardLibraries.EspressoCore)
    androidTestImplementation(StandardLibraries.EspressoContrib)
    androidTestImplementation(StandardLibraries.NavigationTesting)
}