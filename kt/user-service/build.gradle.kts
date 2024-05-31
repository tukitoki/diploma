import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.ir.backend.js.compile

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
    api(libs.spring.boot.starter.web)
    api(libs.spring.boot.starter.validation)
    api(libs.spring.security.crypto)

    implementation(platform(libs.spring.cloud.bom))
    api(libs.spring.cloud.starter.eureka.client)

    api(libs.exposed.starter)
    api(libs.exposed.java.time)

    api(libs.postgesql.driver)

    api(libs.springdoc.openapi.starter.webmvc.ui)

    implementation("ru.vsu.cs.raspopov:shared")
}

springBoot {
    buildInfo {
        properties {
            name = project.name
            version = project.version.toString()
        }
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks {
    test {
        useJUnitPlatform()
    }

    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "21"
        }
    }
}
