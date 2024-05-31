plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.spring.dep.management)
    application
}

group = "ru.vsu.cs.raspopov"
version = "1.00"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(libs.spring.cloud.bom))
    implementation(libs.spring.cloud.starter.eureka.server)

    val gsonVersion = "2.10.1"
    implementation("com.google.code.gson:gson:$gsonVersion")
}