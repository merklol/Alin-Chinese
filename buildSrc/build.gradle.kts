plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.1.0-alpha03")
    implementation(kotlin("gradle-plugin", version = "1.5.30-M1"))
}