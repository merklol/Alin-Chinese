import dependencies.implementAuthentication
import dependencies.implementDaggerHilt
import dependencies.implementMockk
import dependencies.implementNavigation
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
    implementAuthentication()
    implementDaggerHilt()
    implementNavigation()

    implementation(project(":core"))
    implementation(project(":core-ui"))
    implementation(project(":features:authentication"))

    implementMockk()
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation(project(":test-utils"))
    debugImplementation("androidx.fragment:fragment-testing:1.3.6")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.navigation:navigation-testing:2.3.5")
}