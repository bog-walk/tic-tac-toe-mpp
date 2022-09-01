import org.jetbrains.compose.compose

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
    implementation("androidx.navigation:navigation-compose:2.5.1")

    testImplementation(compose("org.jetbrains.compose.ui:ui-test-junit4"))
    testImplementation("junit:junit:4.13.2")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "dev.bogwalk.android"
        minSdk = 27
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