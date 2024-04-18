plugins {
}

group = "ru.vsu.cs.raspopov"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    api(libs.exposed.starter)
    api(libs.exposed.java.time)
}

kotlin {
    jvmToolchain(21)
}