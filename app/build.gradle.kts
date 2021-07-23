import dependencies.implementAuthentication
import dependencies.implementDaggerHilt
import dependencies.implementFirestore
import dependencies.implementNavigation
import dependencies.implementPaging3
import dependencies.implementRoom

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "com.maximapps.alinchinese"
        minSdk = 23
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    implementRoom()
    implementPaging3()
    implementFirestore()
    implementDaggerHilt()
    implementNavigation()
    implementAuthentication()

    implementation(project(":main"))
    implementation(project(":features:home"))
    implementation(project(":features:page"))
    implementation(project(":core"))
    implementation(project(":features:profile"))
    implementation(project(":core-ui"))
    implementation(project(":features:favorites"))
    implementation(project(":features:authentication"))
}