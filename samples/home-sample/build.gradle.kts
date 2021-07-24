import dependencies.implementationOfHilt
import dependencies.implementationOfFirestore
import dependencies.implementationOfMockk
import dependencies.implementationOfNavigation
import dependencies.implementationOfPaging3
import dependencies.implementationOfRoom
import dependencies.androidTestImplementationOfRoom
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
    implementationOfRoom()
    implementationOfPaging3()
    implementationOfFirestore()
    implementationOfHilt()
    implementationOfNavigation()

    implementation(project(":core"))
    implementation(project(":core-ui"))
    implementation(project(":features:home"))
    implementation(project(":features:page"))

    implementationOfMockk()
    androidTestImplementationOfRoom()

    androidTestImplementation(project(":test-utils"))
    debugImplementation("androidx.fragment:fragment-testing:1.3.6")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.4.0")
    androidTestImplementation("androidx.navigation:navigation-testing:2.3.5")
}