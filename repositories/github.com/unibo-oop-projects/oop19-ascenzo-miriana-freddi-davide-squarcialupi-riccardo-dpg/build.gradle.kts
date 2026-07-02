import de.aaschmid.gradle.plugins.cpd.Cpd

plugins {
    java
    checkstyle
    pmd
    application
    /*
     * Adds tasks to export a runnable jar.
     * In order to create it, launch the "shadowJar" task.
     * The runnable jar will be found in build/libs/projectname-all.jar
     */
    id("com.github.johnrengelman.shadow") version "5.2.0"
    id("de.aaschmid.cpd") version "3.1"
    id("com.github.spotbugs") version "4.3.0"
    `build-dashboard`
}

sourceSets {
	main {
		resources {
			srcDirs("resources")
			}
		}
   test {
		resources {
			srcDirs("resources")
			}
		}
}

repositories {
    mavenCentral()
}

val javaFXModules = listOf (
        "base",
        "controls",
        "fxml",
        "graphics",
	    "media"
)

val supportedPlatforms = listOf(
        "linux",
        "mac",
        "win"
)

dependencies {
    //JavaFX modules
    for(platform in supportedPlatforms) {
        for(module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:13:$platform")
        }
    }

    implementation("org.apache.commons:commons-lang3:3.8.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.+")


    //TEST DEPENDENCIES
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testImplementation("org.mockito:mockito-core:3.3.3")
    testImplementation("org.mockito:mockito-junit-jupiter:3.3.3")
    testImplementation("org.testfx:testfx-junit5:4.0.16-alpha")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")
}

tasks.withType<Test> {
    // Enables JUnit 5 Jupiter module
    useJUnitPlatform()
}

application {
    mainClassName = "it.dpg.maingame.launcher.Launcher"
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
