import org.gradle.api.tasks.testing.logging.TestLogEvent
plugins {
    java
    application

    id("com.gradleup.shadow") version "9.3.1"
    id("org.danilopianini.gradle-java-qa") version "1.164.0"

    id("org.danilopianini.unibo-oop-gradle-plugin") version "2.0.22"
}

java { toolchain { languageVersion.set(JavaLanguageVersion.of(21)) } }


repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("org.mockito:mockito-core:5.11.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.11.0")
    
    compileOnly("com.github.spotbugs:spotbugs-annotations:4.8.3")
}

application {
    mainClass.set("it.unibo.burraco.BurracoApp")
}

tasks.processResources {
    from("src/main/resources")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging {
        events(*(TestLogEvent.entries.toTypedArray())) 
    }
    testLogging.showStandardStreams = true 
}

tasks.shadowJar {
    archiveClassifier.set("")
    manifest {
        attributes["Main-Class"] = "it.unibo.burraco.BurracoApp"
    }
}

spotbugs {
    ignoreFailures.set(true)
}

tasks.distZip {
    dependsOn(tasks.shadowJar)
}
tasks.distTar {
    dependsOn(tasks.shadowJar)
}
tasks.startScripts {
    dependsOn(tasks.shadowJar)
}

tasks.named("startShadowScripts") {
    dependsOn(tasks.jar)
}
