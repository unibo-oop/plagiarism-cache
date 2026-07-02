import de.aaschmid.gradle.plugins.cpd.Cpd

plugins {
	java
	application
	id("org.openjfx.javafxplugin") version "0.0.9"

	/*
     * Adds tasks to export a runnable jar.
     * In order to create it, launch the "shadowJar" task.
     * The runnable jar will be found in build/libs/projectname-all.jar
     */
    id("com.github.johnrengelman.shadow") version "6.1.0"

    checkstyle
    pmd
    id("de.aaschmid.cpd")
    id("com.github.spotbugs")
    `build-dashboard`
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

	// JavaFX: comment out if you do not need them
    for (platform in supportedPlatforms) {
        for (module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:$javaFxVersion:$platform")
        }
    }

    implementation("junit:junit:_")
    implementation("org.junit.jupiter:junit-jupiter-api:_")
    runtimeOnly("org.junit.jupiter:junit-jupiter-engine")
    runtimeOnly("org.junit.vintage:junit-vintage-engine")
    File("lib")
        .takeIf { it.exists() }
        ?.takeIf { it.isDirectory }
        ?.listFiles()
        ?.filter { it.extension == "jar" }
        ?.forEach { implementation(files("lib/${it.name}")) }

    // https://mvnrepository.com/artifact/commons-io/commons-io
	implementation("commons-io:commons-io:2.8.0")

	// https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
	implementation("org.apache.commons:commons-lang3:3.12.0")

	implementation("com.password4j:password4j:1.5.2")

	implementation("com.google.guava:guava:30.1.1-jre")


}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
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
    mainClass.set("iuniversity.Launcher")

    /*
     * mainClassName was deprecated by Gradle, but it is still required by John Engelman's Shadow plugin.
     * A pull request with a fix was already merged, but it hasn't been released yet;
     * see https://github.com/johnrengelman/shadow/issues/609 and https://github.com/johnrengelman/shadow/pull/612
     */
    @Suppress("DEPRECATION")
    mainClassName = mainClass.get()
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
