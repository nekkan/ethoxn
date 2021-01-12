rootProject.name = "ethoxn"
enableFeaturePreview("GRADLE_METADATA")

pluginManagement {
    val kotlin = "1.4.30-M1"
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        kotlin("multiplatform") version kotlin
        kotlin("jvm") version kotlin
    }
}
