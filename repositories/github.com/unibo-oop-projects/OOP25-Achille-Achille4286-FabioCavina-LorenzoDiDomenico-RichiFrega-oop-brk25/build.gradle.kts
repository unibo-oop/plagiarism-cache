
plugins {
    java
    application
    id("com.gradleup.shadow") version "8.3.6"
    id("org.danilopianini.gradle-java-qa") version "1.75.0"

    id("org.danilopianini.unibo-oop-gradle-plugin") version "2.0.22"
}

java { toolchain { languageVersion.set(JavaLanguageVersion.of(21)) } }


group = "it.unibo.breakout"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    compileOnly("com.github.spotbugs:spotbugs-annotations:4.8.3")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

application {
    mainClass.set("it.unibo.breakout.App")
}

tasks.test {
    useJUnitPlatform()
}