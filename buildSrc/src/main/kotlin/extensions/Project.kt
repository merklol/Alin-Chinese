package extensions

import com.android.build.api.dsl.JniLibsPackagingOptions
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.ResolutionStrategy
import org.gradle.kotlin.dsl.configure

fun Project.configuration(applicationId: String, testInstrumentationRunner: String) =
    configure<BaseAppModuleExtension> {
        defaultConfig {
            this.applicationId = applicationId
            this.testInstrumentationRunner = testInstrumentationRunner
        }
    }

fun Project.configuration(testInstrumentationRunner: String) =
    configure<LibraryExtension> {
        defaultConfig {
            this.testInstrumentationRunner = testInstrumentationRunner
        }
    }

fun Project.configuration(
    applicationId: String,
    testInstrumentationRunner: String,
    resolutionStrategy: Action<in ResolutionStrategy>,
    jniLibs: JniLibsPackagingOptions.() -> Unit
) =
    configure<BaseAppModuleExtension> {
        defaultConfig {
            this.applicationId = applicationId
            this.testInstrumentationRunner = testInstrumentationRunner
            configurations.all {
                this.resolutionStrategy(resolutionStrategy)
            }
        }

        packagingOptions {
            this.jniLibs(jniLibs)
        }
    }
