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
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:${project.properties["ktor_version"]}")
    implementation("io.ktor:ktor-server-netty-jvm:${project.properties["ktor_version"]}")
    implementation("io.ktor:ktor-server-status-pages-jvm:${project.properties["ktor_version"]}")
    implementation("io.ktor:ktor-server-default-headers-jvm:${project.properties["ktor_version"]}")
    implementation("io.ktor:ktor-server-content-negotiation:${project.properties["ktor_version"]}")
    implementation("io.ktor:ktor-serialization-kotlinx-json:${project.properties["ktor_version"]}")
    implementation("io.ktor:ktor-server-cors:${project.properties["ktor_version"]}")
    implementation("com.github.jillesvangurp:es-kotlin-client:${project.properties["es_kotlin_client_version"]}")
    implementation("ch.qos.logback:logback-classic:${project.properties["logback_version"]}")
    implementation("com.google.guava:guava:${project.properties["guava_version"]}")
    implementation("io.ktor:ktor-client-core:${project.properties["ktor_version"]}")
    implementation("io.ktor:ktor-client-content-negotiation:${project.properties["ktor_version"]}")
    implementation("io.ktor:ktor-client-serialization:${project.properties["ktor_version"]}")

    testImplementation(kotlin("test"))
    testImplementation("io.ktor:ktor-server-test-host:${project.properties["ktor_version"]}")
    testImplementation("io.mockk:mockk:${project.properties["mockk_version"]}")
    testImplementation("org.junit.jupiter:junit-jupiter:${project.properties["junit_version"]}")
    testImplementation("net.javacrumbs.json-unit:json-unit-assertj:${project.properties["jsonunit_assertj_version"]}")
    testImplementation("org.assertj:assertj-core:${project.properties["assertj_version"]}")
}

tasks.jar {
    manifest.attributes["Main-Class"] = "com.tw.ApplicationKt"
    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree)
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
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