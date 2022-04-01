pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    plugins {
        kotlin("jvm") version "1.6.10"
        kotlin("multiplatform") version "1.6.10"
        kotlin("android") version "1.6.10"
        id("com.android.application") version "7.0.4"
        id("com.android.library") version "7.0.4"
        id("org.jetbrains.compose") version "1.1.0"
    }
}

rootProject.name = "tic-tac-toe"

include(":common", ":desktop", ":android")