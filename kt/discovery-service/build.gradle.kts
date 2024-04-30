plugins {
    alias(libs.plugins.spring.dep.management)
    alias(libs.plugins.kotlin.spring)
}

version = "1.00"

dependencies {
    implementation(platform(libs.spring.cloud.bom))
    api(libs.spring.cloud.starter.eureka.server)

    val gsonVersion = "2.10.1"
    implementation("com.google.code.gson:gson:$gsonVersion")
}
