import com.android.build.gradle.LibraryExtension

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-convention")
}

configure<LibraryExtension> {
    compileSdk = 30

    defaultConfig {
        minSdk = 23
        targetSdk = 30
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

    buildFeatures {
        viewBinding = true
    }

    dependencies {
        implementation("androidx.core:core-ktx:1.6.0")
        implementation("androidx.appcompat:appcompat:1.3.1")
        implementation("com.google.android.material:material:1.4.0")
    }
}
