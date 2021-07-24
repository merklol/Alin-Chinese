import dependencies.implementDaggerHilt
import dependencies.implementFirestore
import dependencies.implementNavigation
import dependencies.implementPaging3
import dependencies.implementViewBindingDelegate

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

    implementPaging3()
    implementFirestore()
    implementDaggerHilt()
    implementNavigation()
    implementViewBindingDelegate()

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}