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
        val hostOs = System.getProperty("os.name")
        val isMingwX64 = hostOs.startsWith("Windows")

        val nativeTarget = when {
            hostOs == "Mac OS X" -> macosX64("native")
            hostOs == "Linux" -> linuxX64("native")
            isMingwX64 -> mingwX64("native")
            else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
        }

        jvm {
            compilations.all {
                kotlinOptions.jvmTarget = "${rootProject.extra["jvm_target"]}"
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
            val nativeMain by getting
            val nativeTest by getting
        }
    }

}
