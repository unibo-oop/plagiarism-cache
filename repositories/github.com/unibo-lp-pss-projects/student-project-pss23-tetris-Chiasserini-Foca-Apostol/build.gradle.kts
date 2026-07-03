plugins {
    id("org.danilopianini.unibo-oop-gradle-plugin") version "1.0.7-dev03-64cbefe"
    id("com.github.johnrengelman.shadow") version "8.1.0"
    java
    application
    checkstyle
}

repositories {
    mavenCentral() // Config for download Mavan dependencies
}

dependencies {
    // Dependencies for write the JUnitTest
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
    // Dependencies for run the JUnitTest
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.1")
}

tasks.test {
    useJUnitPlatform() // Configure task "test" for use JUnit Platform
    testLogging { events("passed", "skipped", "failed") } // Print Test's result
}

application { // Config for specify the main class 
    mainClass.set("it.unibo.tetris.Main")
}

sourceSets {
    main {
        resources {
            srcDirs("src/res")
        }
    }
}
