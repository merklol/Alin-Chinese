import dependencies.implementDaggerHilt
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
    implementDaggerHilt()
    implementViewBindingDelegate()
    implementUniFlow()

    implementation(project(":core-ui"))
    implementation(project(":core"))
    implementation(project(":features:page"))

    implementRoomTesting()
    
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")

    testImplementation("junit:junit:4.13.2")
}