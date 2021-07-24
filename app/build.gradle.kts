import dependencies.implementAuthentication
import dependencies.implementDaggerHilt
import dependencies.implementFirestore
import dependencies.implementNavigation
import dependencies.implementPaging3
import dependencies.implementRoom
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
    implementRoom()
    implementPaging3()
    implementFirestore()
    implementDaggerHilt()
    implementNavigation()
    implementAuthentication()

    implementation(project(":main"))
    implementation(project(":features:home"))
    implementation(project(":features:page"))
    implementation(project(":core"))
    implementation(project(":features:profile"))
    implementation(project(":core-ui"))
    implementation(project(":features:favorites"))
    implementation(project(":features:authentication"))
}