package extensions

import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
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
