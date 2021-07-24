import dependencies.implementationOfHilt
import dependencies.implementationOfNavigation
import extensions.configuration

plugins {
    `android-app-convention`
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

configuration(
    applicationId = "com.maximapps.profilesample",
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner",
)

dependencies {
    implementationOfHilt()
    implementationOfNavigation()

    implementation(project(":core-ui"))
    implementation(project(":features:profile"))
    implementation(project(":core"))

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}