plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dep.management)
    alias(libs.plugins.kotlin.spring)
}

group = "ru.vsu.cs.raspopov"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    api(libs.spring.boot.starter.web)
    api(libs.spring.boot.starter.security)

    implementation(platform(libs.spring.cloud.bom))
    api(libs.spring.cloud.starter.eureka.client)

    api(libs.springdoc.openapi.starter.webmvc.ui)

    api(libs.exposed.starter)

    api(libs.postgesql.driver)

    implementation(project(":shared"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}