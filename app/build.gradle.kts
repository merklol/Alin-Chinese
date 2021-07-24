import dependencies.implementationOfAuthentication
import dependencies.implementationOfHilt
import dependencies.implementationOfFirestore
import dependencies.implementationOfNavigation
import dependencies.implementationOfPaging3
import dependencies.implementationOfRoom
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
    implementationOfRoom()
    implementationOfPaging3()
    implementationOfFirestore()
    implementationOfHilt()
    implementationOfNavigation()
    implementationOfAuthentication()

    implementation(project(":main"))
    implementation(project(":features:home"))
    implementation(project(":features:page"))
    implementation(project(":core"))
    implementation(project(":features:profile"))
    implementation(project(":core-ui"))
    implementation(project(":features:favorites"))
    implementation(project(":features:authentication"))
}