plugins {
    id("org.jetbrains.compose") version "0.3.0-build152"
    id("com.android.application")
    kotlin("android")
}

group = "com.darkrockstudios.apps"
version = "1.0"

repositories {
    google()
}

dependencies {
    api(project(":common"))

    implementation("androidx.activity:activity-compose:1.3.0-alpha02") {
        exclude(group = "androidx.compose.animation")
        exclude(group = "androidx.compose.foundation")
        exclude(group = "androidx.compose.material")
        exclude(group = "androidx.compose.runtime")
        exclude(group = "androidx.compose.ui")
    }
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.3.0")
    implementation("com.google.android.material:material:1.3.0")

    implementation("com.arkivanov.decompose:decompose:0.1.8")
    implementation("com.arkivanov.decompose:extensions-compose-jetbrains:0.1.8")
    //implementation("com.arkivanov.decompose:extensions-android:0.1.8")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.darkrockstudios.apps.android"
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}