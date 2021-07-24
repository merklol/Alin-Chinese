import dependencies.implementCoil
import dependencies.implementDaggerHilt
import dependencies.implementGson
import dependencies.implementNavigation
import dependencies.implementRoomTesting
import dependencies.implementUniFlow
import dependencies.implementViewBindingDelegate

plugins {
    `android-feature-convention`
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

dependencies {
    implementCoil()
    implementGson()
    implementUniFlow()
    implementDaggerHilt()
    implementNavigation()
    implementViewBindingDelegate()

    implementation(project(":core-ui"))
    implementation(project(":core"))

    implementRoomTesting()
    testImplementation("junit:junit:4.13.2")
}