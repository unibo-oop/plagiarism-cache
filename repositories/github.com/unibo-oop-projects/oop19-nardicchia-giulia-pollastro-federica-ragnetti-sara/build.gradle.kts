// Declaration of the Gradle extension to use
plugins {
    java
    application
    /*
     * Adds tasks to export a runnable jar.
     * In order to create it, launch the "shadowJar" task.
     * The runnable jar will be found in build/libs/projectname-all.jar
     */
    id("com.github.johnrengelman.shadow") version "5.2.0"
}
repositories {
    jcenter() // Contains the whole Maven Central + other stuff
}
// List of JavaFX modules you need. Comment out things you are not using.
val javaFXModules = listOf(
    "base",
    "controls",
    "fxml",
    "swing",
    "graphics"
)
// All required for OOP
val supportedPlatforms = listOf("linux", "mac", "win")

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.11.0.rc1")
    implementation("com.fasterxml.jackson.core:jackson-core:2.11.0.rc1")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.11.0.rc1")
    implementation("org.apache.commons:commons-lang3:3.10")
    implementation("io.github.classgraph:classgraph:4.8.86")
    testCompile("org.testfx:testfx-junit5:4.0.16-alpha")

    // Example library: Guava. Add what you need (and remove Guava if you don't use it)
    implementation("com.google.guava:guava:28.1-jre")
    // JavaFX: comment out if you do not need them
    for (platform in supportedPlatforms) {
        for (module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:13:$platform")
        }
    }
    
    
    // JUnit API and testing engine
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")
}

tasks.withType<Test> {
    // Enables JUnit 5 Jupiter module
    useJUnitPlatform()
}

application {
    mainClassName = "application.Main"
}
