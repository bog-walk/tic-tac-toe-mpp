plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
}

group = "dev.bogwalk"
version = "1.0"

dependencies {
    implementation(project(":common"))
    implementation("androidx.activity:activity-compose:1.5.1")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "dev.bogwalk.android"
        minSdk = 24
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}