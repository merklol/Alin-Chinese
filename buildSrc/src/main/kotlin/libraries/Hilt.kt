package dependencies

import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.implementationOfHilt() {
    "implementation"("com.google.dagger:hilt-android:${Versions.hilt}")
    "kapt"("com.google.dagger:hilt-compiler:${Versions.hilt}")

    "androidTestImplementation"(
        "com.google.dagger:hilt-android-testing:${Versions.hilt}"
    )
    "kaptAndroidTest"("com.google.dagger:hilt-compiler:${Versions.hilt}")
}
