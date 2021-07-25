import libraries.implementationOfHilt
import libraries.implementationOfNavigation
import libraries.implementationOfRoom

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