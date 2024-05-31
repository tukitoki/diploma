import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dep.management)
    alias(libs.plugins.kotlin.spring)
    application
}

group = "ru.vsu.cs.raspopov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.jetbrains.kotlin.reflect)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.spring.boot.starter.data.redis)

    implementation(platform(libs.spring.cloud.bom))
    implementation(libs.spring.cloud.starter.eureka.client)
    implementation(libs.spring.cloud.starter.openfeign)

    implementation(libs.redis.client.jedis)

    val jwtHoseVersion = "9.37.3"
    implementation("com.nimbusds:nimbus-jose-jwt:$jwtHoseVersion")

    implementation(libs.springdoc.openapi.starter.webmvc.ui)

    implementation(libs.org.apache.log4j.kotlin)
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
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