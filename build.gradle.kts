// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.1.0-alpha04")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.20")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.37")
        classpath("com.google.gms:google-services:4.3.8")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.5")
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt") version "1.17.0"
}

subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")

    detekt {
        toolVersion = "1.17.0"
        config = files("$rootDir/config/detekt/detekt.yml")
        buildUponDefaultConfig = true
        allRules = true

        reports {
            html {
                enabled = true
                destination = file("$rootDir/reports/detekt/detekt.html")
            }
        }
    }
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    jvmTarget = "1.8"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}