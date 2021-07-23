import dependencies.implementDaggerHilt
import dependencies.implementFirestore
import dependencies.implementMockk
import dependencies.implementNavigation
import dependencies.implementPaging3
import dependencies.implementRoom
import dependencies.implementRoomTesting

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "com.maximapps.homesample"
        minSdk = 23
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.maximapps.homesample.hilt.HiltTestRunner"

        configurations.all {
            resolutionStrategy {
                force("androidx.test:monitor:1.4.0-rc01")
            }
        }
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

    implementation(project(":core"))
    implementation(project(":core-ui"))
    implementation(project(":features:home"))
    implementation(project(":features:page"))

    implementMockk()
    implementRoomTesting()

    androidTestImplementation(project(":test-utils"))
    debugImplementation("androidx.fragment:fragment-testing:1.3.6")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.4.0")
    androidTestImplementation("androidx.navigation:navigation-testing:2.3.5")
}