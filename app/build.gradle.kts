import libraries.implementationOfAuthentication
import libraries.implementationOfHilt
import libraries.implementationOfFirestore
import libraries.implementationOfNavigation
import libraries.implementationOfPaging3
import libraries.implementationOfRoom
import extensions.configuration

plugins {
    `android-app-convention`
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("androidx.navigation.safeargs.kotlin")
}

configuration(
    applicationId = "com.maximapps.alinchinese",
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner",
)

dependencies {
    implementation(project(Modules.Core))
    implementation(project(Modules.CoreUI))
    implementation(project(Features.Main))
    implementation(project(Features.Home))
    implementation(project(Features.Page))
    implementation(project(Features.Profile))
    implementation(project(Features.Favorites))
    implementation(project(Features.Authentication))

    implementationOfRoom()
    implementationOfHilt()
    implementationOfPaging3()
    implementationOfFirestore()
    implementationOfNavigation()
    implementationOfAuthentication()
}