plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dep.management)
    alias(libs.plugins.kotlin.spring)
    application
}

repositories {
    mavenCentral()
}

dependencies {
    api(libs.spring.boot.starter.web)
    api(libs.spring.boot.starter.validation)
    api(libs.spring.security.crypto)

    implementation(platform(libs.spring.cloud.bom))
    api(libs.spring.cloud.starter.eureka.client)

    api(libs.exposed.starter)
    api(libs.exposed.java.time)

    api(libs.postgesql.driver)

    api(libs.liquidbase.core)

    api(libs.springdoc.openapi.starter.webmvc.ui)

    implementation("ru.vsu.cs.raspopov:shared")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

springBoot {
    buildInfo {
        properties {
            name = project.name
            version = project.version.toString()
        }
    }
}