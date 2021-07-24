import dependencies.implementDaggerHilt
import dependencies.implementNavigation
import dependencies.implementViewBindingDelegate

plugins {
    `android-library-convention`
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementNavigation()
    implementDaggerHilt()
    implementViewBindingDelegate()

    implementation(project(":core-ui"))
    implementation(project(":core"))
}