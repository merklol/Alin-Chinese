import dependencies.implementationOfHilt
import dependencies.implementationOfFirestore
import dependencies.implementationOfNavigation
import dependencies.implementationOfPaging3
import dependencies.implementationOfViewBindingDelegate

plugins {
    `android-library-convention`
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-ui"))
    implementation(project(":features:home"))
    implementation(project(":features:profile"))
    implementation(project(":features:favorites"))

    implementationOfPaging3()
    implementationOfFirestore()
    implementationOfHilt()
    implementationOfNavigation()
    implementationOfViewBindingDelegate()

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}