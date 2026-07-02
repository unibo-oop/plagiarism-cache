plugins {
    java
    application
    id("org.danilopianini.gradle-java-qa") version "1.10.0"
    id("org.openjfx.javafxplugin") version "0.0.13"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    val junitVersion = "5.9.3"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")

    // SpotBugs
    compileOnly("com.github.spotbugs:spotbugs-annotations:4.7.3")

    // JavaFx
    val javaFxVersion = "20"
    val javaFxModules = listOf("base","controls","fxml","swing","graphics")
    val supportedSystems = listOf("win", "linux", "mac")
    for (module in javaFxModules) {
        for (sys in supportedSystems) {
            implementation("org.openjfx:javafx-$module:$javaFxVersion:$sys")
        }
    }
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.googlecode.json-simple:json-simple:1.1.1")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.13.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.0")

}

tasks.test {
    useJUnitPlatform()
    testLogging { events("passed", "skipped", "failed") }
    testLogging.showStandardStreams = true
}

application {
    mainClass.set("it.tbt.TurnBasedTunnels")
}

