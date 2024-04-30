plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dep.management)
    alias(libs.plugins.kotlin.spring)
}

version = "0.0.1"

dependencies {
    api(libs.spring.boot.starter.web)
    api(libs.spring.boot.starter.validation)
    api(libs.spring.boot.starter.security)
    api(libs.spring.boot.starter.data.redis)

    implementation(platform(libs.spring.cloud.bom))
    api(libs.spring.cloud.starter.eureka.client)
    api(libs.spring.cloud.starter.openfeign)

    api(libs.redis.client.jedis)

    val jwtHoseVersion = "9.37.3"
    implementation("com.nimbusds:nimbus-jose-jwt:$jwtHoseVersion")

    api(libs.springdoc.openapi.starter.webmvc.ui)

    api(libs.org.apache.log4j.kotlin)
}

tasks.bootBuildImage {

}