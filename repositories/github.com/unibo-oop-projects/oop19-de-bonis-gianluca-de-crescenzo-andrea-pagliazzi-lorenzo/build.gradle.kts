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
    jcenter() // Contains the whole Maven Central + other stuff
}
// List of JavaFX modules you need. Comment out things you are not using.
val javaFXModules = listOf(
    "base",
    "controls",
    "fxml",
    //"swing",
    "graphics",
    "media"
    
)
// All required for OOP
val supportedPlatforms = listOf("linux", "mac", "win")

dependencies {
    implementation("com.google.code.gson:gson:2.8.6")
    // JavaFX: comment out if you do not need them
    for (platform in supportedPlatforms) {
        for (module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:11:$platform")
        }
    }
	
	// JUnit API and testing engine
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")
    
    //Junit4
    testImplementation("junit:junit:4.13")
	testCompile("org.testfx:testfx-core:4.0.15-alpha")
    testCompile("org.testfx:testfx-junit:4.0.15-alpha")  
}

java {                                      
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

application {
    mainClassName = "zombieversity.controller.core.GameLauncher"
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
