
plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("org.danilopianini.gradle-java-qa") version "1.108.0"

    id("org.danilopianini.unibo-oop-gradle-plugin") version "1.1.71"
}

java { toolchain { languageVersion.set(JavaLanguageVersion.of(21)) } }


repositories {
    mavenCentral()
}

application {
    mainClass.set("ArmataTenebre")
}

dependencies {
    compileOnly("com.github.spotbugs:spotbugs-annotations:4.7.3")
    implementation("org.apache.commons:commons-lang3:3.12.0")

    val jUnitVersion = "5.9.2"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")

    val mockitoVersion = "4.11.0"
    testImplementation("org.mockito:mockito-core:$mockitoVersion")
    testImplementation("org.mockito:mockito-junit-jupiter:$mockitoVersion")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events(*org.gradle.api.tasks.testing.logging.TestLogEvent.values())
        showStandardStreams = true
    }
}
