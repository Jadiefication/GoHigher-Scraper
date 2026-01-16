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
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
    implementation("it.skrape:skrapeit:1.2.2")
}

kotlin {
    jvmToolchain(24)
}

tasks.test {
    useJUnitPlatform()
}