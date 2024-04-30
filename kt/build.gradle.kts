import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dep.management)
}

repositories {
    mavenCentral()
    mavenLocal()
    gradlePluginPortal()
}

subprojects {
    apply {
        plugin(rootProject.libs.plugins.jvm.get().pluginId)
        plugin(rootProject.libs.plugins.spring.boot.get().pluginId)
        plugin(rootProject.libs.plugins.spring.dep.management.get().pluginId)
    }

    repositories {
        mavenCentral()
        mavenLocal()
    }

    dependencies {
        api(rootProject.libs.jetbrains.kotlin.gradle.plugin)
        api(rootProject.libs.jetbrains.kotlin.reflect)
        api(rootProject.libs.jackson.kotlin.module)
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "21"
        }
    }

    apply(plugin = rootProject.libs.plugins.jvm.get().pluginId)
}

tasks.bootBuildImage {
    enabled = false
}