pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
        maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
    }
    
}
rootProject.name = "notional"

enableFeaturePreview("GRADLE_METADATA")

include(":android")
include(":desktop")
include(":common")

