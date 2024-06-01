plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dep.management)
    alias(libs.plugins.kotlin.spring)
    application
}

group = "ru.vsu.cs.raspopov"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    api(libs.jackson.kotlin.module)

    api(libs.spring.boot.starter.web)
    api(libs.spring.boot.starter.security)

    implementation(platform(libs.spring.cloud.bom))
    api(libs.spring.cloud.starter.eureka.client)

    api(libs.springdoc.openapi.starter.webmvc.ui)

    api(libs.exposed.starter)

    api(libs.postgesql.driver)

    api(libs.jetbrains.kotlin.gradle.plugin)
    api(libs.jetbrains.kotlin.reflect)
    api(libs.jackson.kotlin.module)

    implementation("ru.vsu.cs.raspopov:shared")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}