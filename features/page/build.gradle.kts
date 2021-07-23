import dependencies.implementCoil
import dependencies.implementDaggerHilt
import dependencies.implementGson
import dependencies.implementNavigation
import dependencies.implementRoomTesting
import dependencies.implementUniFlow
import dependencies.implementViewBindingDelegate

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = 30

    defaultConfig {
        minSdk = 23
        targetSdk = 30

        testInstrumentationRunner = "com.maximapps.page.HiltTestRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    implementCoil()
    implementGson()
    implementUniFlow()
    implementDaggerHilt()
    implementNavigation()
    implementViewBindingDelegate()

    implementation(project(":core-ui"))
    implementation(project(":core"))
    
    implementRoomTesting()
    testImplementation("junit:junit:4.13.2")
}