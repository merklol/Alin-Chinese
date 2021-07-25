package libraries

import org.gradle.kotlin.dsl.DependencyHandlerScope

/**
 * Adds Dagger Hilt to the project.
 *
 * @since 0.5.2
 */
fun DependencyHandlerScope.implementationOfHilt() {
    "implementation"("com.google.dagger:hilt-android:${LibraryVersions.Hilt}")
    "kapt"("com.google.dagger:hilt-compiler:${LibraryVersions.Hilt}")

    "androidTestImplementation"(
        "com.google.dagger:hilt-android-testing:${LibraryVersions.Hilt}"
    )
    "kaptAndroidTest"("com.google.dagger:hilt-compiler:${LibraryVersions.Hilt}")
}
