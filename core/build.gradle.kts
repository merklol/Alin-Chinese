import dependencies.implementDaggerHilt
import dependencies.implementNavigation
import dependencies.implementRoom

plugins {
    `android-library-convention`
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementRoom()
    implementDaggerHilt()
    implementNavigation()
}