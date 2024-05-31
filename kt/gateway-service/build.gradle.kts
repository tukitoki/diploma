plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.spring.dep.management)
    alias(libs.plugins.kotlin.spring)
    application
}

version = "1.00"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(libs.spring.cloud.bom))
    api(libs.spring.cloud.starter.gateway)
    api(libs.spring.cloud.starter.loadbalancer)
    api(libs.spring.cloud.starter.eureka.client)

    val gsonVersion = "2.10.1"
    implementation("com.google.code.gson:gson:$gsonVersion")

    api(libs.springdoc.openapi.starter.webmvc.ui)
    api(libs.springdoc.openapi.starter.webflux.ui)

    runtimeOnly("io.netty:netty-resolver-dns-native-macos:4.1.107.Final:osx-aarch_64")
}
