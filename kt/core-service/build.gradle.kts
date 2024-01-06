
plugins {
    id("kt.kotlin-application-conventions")

    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dep.management)
    alias(libs.plugins.kotlin.spring)
}

dependencies {
    api(libs.spring.boot.starter.web)
    api(libs.spring.boot.starter.validation)

    api(libs.exposed.starter)
    api(libs.exposed.java.time)

    api(libs.postgesql.driver)

    api(libs.openapi.ui)
}

version = "0.0.1"

springBoot {
    buildInfo {
        properties {
            name = project.name
            version = project.version.toString()
        }
    }
}