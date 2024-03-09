
plugins {
    id("kt.kotlin-application-conventions")

    alias(libs.plugins.spring.dep.management)
    alias(libs.plugins.kotlin.spring)
}

version = "1.00"

dependencies {
    implementation(libs.jetbrains.kotlin.gradle.plugin)

    implementation(platform(libs.spring.cloud.bom))

    val gsonVersion = "2.10.1"
    implementation("com.google.code.gson:gson:$gsonVersion")

    api(libs.spring.cloud.eureka.server)
}
