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

val javaFXModules = listOf(
    "base",
    "controls",
    "fxml",
    "swing",
    "graphics",
    "media"
)
// All required for OOP
val supportedPlatforms = listOf("linux", "mac", "win")

dependencies {
    for (platform in supportedPlatforms) {
        for (module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:13:$platform")
        }
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

pmd {
    ruleSets = listOf()
    ruleSetConfig = resources.text.fromFile("${project.rootProject.projectDir}/config/pmd/pmd.xml")
    isIgnoreFailures = true
}

checkstyle {
    isIgnoreFailures = true
}

application {
    mainClassName = "bubbleshooter.application.Launcher"
}