plugins {
    alias(libs.plugins.jvm)
    `java-library`
}

repositories {
    mavenCentral()
}

group = "ru.vsu.cs.raspopov"
version = "0.0.1"

dependencies {
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.java.time)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
