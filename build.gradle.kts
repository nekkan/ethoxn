plugins {
    kotlin("multiplatform")
}

rootProject.extra.apply {
    set("kotlin_version", "1.4.30-M1")
    set("jvm_target", "1.8")
}

allprojects {
    plugins.apply("org.jetbrains.kotlin.multiplatform")

    repositories {
        mavenCentral()
        jcenter()
    }

    kotlin {
        val isNativeSupported = when(System.getProperty("os.name")) {
            "Mac OS X" -> macosX64("native")
            "Linux" -> linuxX64("native")
            else -> null
        } != null

        jvm {
            compilations.all {
                kotlinOptions.jvmTarget = "${rootProject.extra["jvm_target"]}"
            }
        }
        sourceSets {
            val commonMain by getting {
                kotlin.srcDir("shared/sources")
                resources.srcDir("shared/resources")
            }
            val jvmMain by getting {
                kotlin.srcDir("jvm/sources")
                resources.srcDir("jvm/resources")
            }
            if(isNativeSupported) {
                val nativeMain by getting {
                    kotlin.srcDir("native/sources")
                    resources.srcDir("native/resources")
                }
            }
        }
    }

}
