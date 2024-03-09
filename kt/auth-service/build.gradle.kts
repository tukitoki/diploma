
plugins {
    id("kt.kotlin-application-conventions")

    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dep.management)
    alias(libs.plugins.kotlin.spring)
}

dependencies {
    implementation(libs.jetbrains.kotlin.gradle.plugin)
    implementation(libs.jetbrains.kotlin.reflect)

    api(libs.spring.boot.starter.web)
    api(libs.spring.boot.starter.validation)
    api(libs.spring.boot.starter.security)
    api(libs.spring.boot.starter.data.redis)

    implementation(platform(libs.spring.cloud.bom))
    api(libs.spring.cloud.eureka.client)

    api(libs.redis.client.jedis)

    val jwtHoseVersion = "9.37.3"
    implementation("com.nimbusds:nimbus-jose-jwt:$jwtHoseVersion")

    api(libs.springdoc.openapi.ui)

    api(libs.org.apache.log4j.kotlin)
}

version = "0.0.1"