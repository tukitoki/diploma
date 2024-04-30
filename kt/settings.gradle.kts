rootProject.name = "kt"
include("core-service")
include("auth-service")
include("discovery-service")
include("gateway-service")
include("shared")
include("order-service")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        mavenLocal()
        google()
    }
}

pluginManagement {
    repositories {
        mavenCentral()
        mavenLocal()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}