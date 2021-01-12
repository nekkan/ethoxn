rootProject.extra.apply {
    set("antlr_dependency", "com.strumenta.antlr-kotlin:antlr-kotlin-runtime:0ad2c42952")
    set("antlr_target", "com.strumenta.antlr-kotlin:antlr-kotlin-target:0ad2c42952")
}

repositories {
    maven("https://jitpack.io/")
}

buildscript {
    repositories {
        mavenCentral()
        maven("https://jitpack.io/")
    }
    dependencies {
        // for some reason we have to define here again
        val antlrPlugin = "com.strumenta.antlr-kotlin:antlr-kotlin-gradle-plugin:0ad2c42952"
        classpath(antlrPlugin)
    }
}

val antlrDependency = rootProject.extra["antlr_dependency"] as? String?
    ?: error("Can't find the value for 'antlr_dependency'.")

val antlrTarget = rootProject.extra["antlr_target"] as? String?
    ?: error("Can't find the value for 'antlr_target'.")

kotlin {
    sourceSets {
        val antlr by creating {
            dependencies {
                api(kotlin("stdlib-common"))
                api(antlrDependency)
            }
            kotlin.srcDir("antlr/sources")
            resources.srcDir("antlr/resources")
            kotlin.srcDir("build/generated-src/shared/sources")
        }
        val commonMain by getting {
            dependsOn(antlr)
        }
        val commonTest by getting {
            dependsOn(antlr)
        }
    }
}

tasks.register<com.strumenta.antlrkotlin.gradleplugin.AntlrKotlinTask>("generateKotlinCommonGrammarSource") {
    antlrClasspath = configurations.detachedConfiguration(project.dependencies.create(antlrTarget))
    maxHeapSize = "64m"
    packageName = "com.nekkan.grammar.generated"
    arguments = listOf("-no-visitor", "-no-listener")
    source = project.objects
        .sourceDirectorySet("antlr", "antlr")
        .srcDir("antlr/sources").apply {
            include("*.g4")
        }
    outputDirectory = File("build/generated-src/shared/sources")
}

tasks.getByName("compileKotlinJvm").dependsOn("generateKotlinCommonGrammarSource")