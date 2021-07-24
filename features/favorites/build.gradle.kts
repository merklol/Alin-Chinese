import dependencies.implementationOfHilt
import dependencies.androidTestImplementationOfRoom
import dependencies.implementationOfUniFlow
import dependencies.implementationOfViewBindingDelegate

plugins {
    `android-library-convention`
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

dependencies {
    implementationOfHilt()
    implementationOfViewBindingDelegate()
    implementationOfUniFlow()

    implementation(project(":core-ui"))
    implementation(project(":core"))
    implementation(project(":features:page"))

    androidTestImplementationOfRoom()
    
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")

    testImplementation("junit:junit:4.13.2")
}