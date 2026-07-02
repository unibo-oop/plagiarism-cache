
plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("org.danilopianini.gradle-java-qa") version "1.34.0"

    id("org.danilopianini.unibo-oop-gradle-plugin") version "1.0.4-dev03-d2a3b5e"
}


repositories {
    mavenCentral()
}

dependencies {
    compileOnly("com.github.spotbugs:spotbugs-annotations:4.8.3")

    val jUnitVersion = "5.10.1"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events(*org.gradle.api.tasks.testing.logging.TestLogEvent.values())
        showStandardStreams = true
    }
}

application {
    mainClass = "it.unibo.fnafretro.Program"
}

 java { toolchain { languageVersion.set(JavaLanguageVersion.of(17)) } }