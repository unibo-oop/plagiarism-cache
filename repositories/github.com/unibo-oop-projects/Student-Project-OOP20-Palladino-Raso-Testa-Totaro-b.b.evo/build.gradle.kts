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
            srcDirs("res")
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

val javaFxVersion = "15.0.1"

dependencies {
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("com.google.inject:guice:5.0.0-BETA-1")
    testImplementation("org.testfx:testfx-core:4.0.16-alpha")
    testImplementation("org.testfx:testfx-junit5:4.0.16-alpha")
    testImplementation(platform("org.junit:junit-bom:5.7.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    //implementation("junit:junit:_")
    implementation("org.junit.jupiter:junit-jupiter-api:_")
    runtimeOnly("org.junit.jupiter:junit-jupiter-engine:_")
    runtimeOnly("org.junit.vintage:junit-vintage-engine:_")
    File("lib")
        .takeIf { it.exists() }
        ?.takeIf { it.isDirectory }
        ?.listFiles()
        ?.filter { it.extension == "jar" }
        ?.forEach { implementation(files("lib/${it.name}")) }
    for (platform in supportedPlatforms) {
      for (module in javaFXModules) {
          implementation("org.openjfx:javafx-$module:$javaFxVersion:$platform")
      }
    }
}

tasks.withType<Test>() {
    ignoreFailures = true
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
    mainClassName = "launcher.Launcher"
}
