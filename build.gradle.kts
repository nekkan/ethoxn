plugins {
    kotlin("multiplatform")
}

allprojects {
    plugins.apply("org.jetbrains.kotlin.multiplatform")

    extra.apply {
        set("kotlin_version", "1.4.30-M1")
        set("jvm_target", "1.8")
    }

    repositories {
        mavenCentral()
        jcenter()
    }

    kotlin {
        jvm {
            compilations.all {
                kotlinOptions.jvmTarget = "${extra["jvm_target"]}"
            }
        }
        sourceSets {
            val commonMain by getting {
                kotlin.srcDir("shared/sources")
                kotlin.srcDir("shared/resources")
            }
            val jvmMain by getting {
                kotlin.srcDir("jvm/sources")
                kotlin.srcDir("jvm/resources")
            }
        }
    }
}
