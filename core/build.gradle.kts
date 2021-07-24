import dependencies.implementDaggerHilt
import dependencies.implementNavigation
import dependencies.implementRoom

plugins {
    `android-feature-convention`
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementRoom()
    implementDaggerHilt()
    implementNavigation()
}