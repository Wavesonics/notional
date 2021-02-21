import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "0.3.0-build152"
}

group = "com.darkrockstudios.apps.notional"
version = "1.0"

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "14"
        }
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                api(project(":common"))
                implementation(compose.desktop.currentOs)

                implementation("com.arkivanov.decompose:decompose:0.1.8")
                implementation("com.arkivanov.decompose:extensions-compose-jetbrains:0.1.8")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "jvm"
        }
    }
}