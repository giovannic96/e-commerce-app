import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization").version("1.6.21")
    application
}

group = "com.tw"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val ktorVersion by extra { "2.0.1" }
val logbackVersion by extra { "1.2.11" }
val mockkVersion by extra { "1.12.3" }
val guavaVersion by extra { "31.1-jre" }
val junitVersion by extra { "5.8.2" }
val assertjVersion by extra { "3.22.0" }
val jsonUnitAssertjVersion by extra { "2.32.0" }

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-status-pages-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-default-headers-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    //implementation("io.github.microutils:kotlin-logging-jvm:2.1.21")
    //implementation("org.slf4j:slf4j-simple:1.7.36")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("com.google.guava:guava:$guavaVersion")

    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("net.javacrumbs.json-unit:json-unit-assertj:$jsonUnitAssertjVersion")
    testImplementation("org.assertj:assertj-core:$assertjVersion")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

application {
    mainClass.set("ApplicationKt")
}