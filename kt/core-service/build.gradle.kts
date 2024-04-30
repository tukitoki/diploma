plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dep.management)
    alias(libs.plugins.kotlin.spring)
}

group = "ru.vsu.cs.raspopov"
version = "0.0.1"

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

    implementation(project(":shared"))
}

springBoot {
    buildInfo {
        properties {
            name = project.name
            version = project.version.toString()
        }
    }
}