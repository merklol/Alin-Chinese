plugins {
    id("com.android.application") apply false
    id("com.android.library") apply false
    kotlin("android") apply false
    id("dagger.hilt.android.plugin") version "2.37" apply false
    id("com.google.gms.google-services") version "4.3.8" apply false
    id("androidx.navigation.safeargs") version "2.3.5" apply false
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

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    jvmTarget = "1.8"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
