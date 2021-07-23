import dependencies.implementCoil
import dependencies.implementCoroutines
import dependencies.implementDaggerHilt
import dependencies.implementFirestore
import dependencies.implementLifecycle
import dependencies.implementNavigation
import dependencies.implementPaging3
import dependencies.implementRoomTesting
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

        testInstrumentationRunner = "com.maximapps.home.HiltTestRunner"
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
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    implementation(project(":core-ui"))
    implementation(project(":core"))
    implementation(project(":features:page"))

    implementCoil()
    implementPaging3()
    implementFirestore()
    implementLifecycle()
    implementCoroutines()
    implementDaggerHilt()
    implementNavigation()
    implementViewBindingDelegate()

    implementRoomTesting()
    testImplementation("junit:junit:4.13.2")
}