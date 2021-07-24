import dependencies.implementCoil
import dependencies.implementCoroutines
import dependencies.implementDaggerHilt
import dependencies.implementFirestore
import dependencies.implementLifecycle
import dependencies.implementNavigation
import dependencies.implementPaging3
import dependencies.implementRoomTesting
import dependencies.implementViewBindingDelegate

plugins {
    `android-feature-convention`
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

dependencies {
    implementation(project(":core-ui"))
    implementation(project(":core"))
    implementation(project(":features:page"))

    implementCoil()
    implementPaging3()
    implementFirestore()
    implementLifecycle()
    implementCoroutines()
    implementDaggerHilt()
    implementNavigation()
    implementViewBindingDelegate()

    implementRoomTesting()
    testImplementation("junit:junit:4.13.2")
}