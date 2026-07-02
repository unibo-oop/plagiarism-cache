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
    jcenter();
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
    for (platform in supportedPlatforms) {
        for (module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:13:$platform")
        }
    }
    // JUnit API and testing engine
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")
	
	testImplementation("junit:junit:4.12")
	
	implementation("org.apache.commons:commons-math3:3.6.1")
	implementation("com.google.guava:guava:28.0-jre")
}

application {
    mainClassName = "it.bomberman.launcher.BomberManLauncher"
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
