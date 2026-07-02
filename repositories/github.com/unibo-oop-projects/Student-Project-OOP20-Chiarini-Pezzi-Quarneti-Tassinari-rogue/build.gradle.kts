import de.aaschmid.gradle.plugins.cpd.Cpd

plugins {
    java
    checkstyle
    pmd
    id("de.aaschmid.cpd")
    id("com.github.spotbugs")
    `build-dashboard`
    application
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

sourceSets {
    main {
        java {
            srcDirs("src")
        }
        resources {
            srcDirs("resources")
        }
    }
    test {
        java {
            srcDirs("src")
        }
    }
}

repositories {
    mavenCentral()
    flatDir {
        dirs("lib")
    }
}


val javaFXModules = listOf(
  "base",
  "controls",
  "fxml",
  "swing",
  "graphics"
)

val supportedPlatforms = listOf("linux", "mac", "win") // All required for OOP

val javaFxVersion = 14


dependencies {
    implementation("junit:junit:_")
    implementation("com.google.guava:guava:29.0-jre")
    implementation("org.slf4j:slf4j-log4j12:1.7.30")
    implementation("org.junit.jupiter:junit-jupiter-api:_")
    for (platform in supportedPlatforms) {
      for (module in javaFXModules) {
          implementation("org.openjfx:javafx-$module:$javaFxVersion:$platform")
      }
    }
    runtimeOnly("org.junit.jupiter:junit-jupiter-engine")
    runtimeOnly("org.junit.vintage:junit-vintage-engine")
    File("lib")
        .takeIf { it.exists() }
        ?.takeIf { it.isDirectory }
        ?.listFiles()
        ?.filter { it.extension == "jar" }
        ?.forEach { implementation(files("lib/${it.name}")) }
}

tasks.withType<Test>() {
    useJUnitPlatform()
}

spotbugs {
    setEffort("max")
    setReportLevel("low")
    showProgress.set(true)
    val excludeFile = File("${project.rootProject.projectDir}/config/spotbugs/excludes.xml")
    if (excludeFile.exists()) {
        excludeFilter.set(excludeFile)
    }
}

tasks.withType<com.github.spotbugs.snom.SpotBugsTask> {
    ignoreFailures = true
    reports {
        create("html") {
            enabled = true
        }
    }
}

pmd {
    ruleSets = listOf()
    ruleSetConfig = resources.text.fromFile("${project.rootProject.projectDir}/config/pmd/pmd.xml")
    isIgnoreFailures = true
}

cpd {
    isIgnoreFailures = true
}

tasks.withType<Cpd> {
    reports {
        xml.setEnabled(false)
        text.setEnabled(true)
    }
    language = "java"
    minimumTokenCount = 50
    ignoreFailures = true
    source = sourceSets["main"].allJava
}

checkstyle {
    isIgnoreFailures = true
}

application {
    // Define the main class for the application
    mainClassName = "rogue.App"
}
