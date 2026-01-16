plugins {
    kotlin("jvm") version "2.2.21"
}

group = "io.void"
version = "1.0-SNAPSHOT"
val jdaVersion = "6.3.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("net.dv8tion:JDA:$jdaVersion")
}

kotlin {
    jvmToolchain(24)
}

tasks.test {
    useJUnitPlatform()
}