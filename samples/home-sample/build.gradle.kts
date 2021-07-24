import dependencies.implementDaggerHilt
import dependencies.implementFirestore
import dependencies.implementMockk
import dependencies.implementNavigation
import dependencies.implementPaging3
import dependencies.implementRoom
import dependencies.implementRoomTesting
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
    implementRoom()
    implementPaging3()
    implementFirestore()
    implementDaggerHilt()
    implementNavigation()

    implementation(project(":core"))
    implementation(project(":core-ui"))
    implementation(project(":features:home"))
    implementation(project(":features:page"))

    implementMockk()
    implementRoomTesting()

    androidTestImplementation(project(":test-utils"))
    debugImplementation("androidx.fragment:fragment-testing:1.3.6")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.4.0")
    androidTestImplementation("androidx.navigation:navigation-testing:2.3.5")
}