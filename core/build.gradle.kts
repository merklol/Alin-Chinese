import dependencies.implementationOfHilt
import dependencies.implementationOfNavigation
import dependencies.implementationOfRoom

plugins {
    `android-library-convention`
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementationOfRoom()
    implementationOfHilt()
    implementationOfNavigation()
}