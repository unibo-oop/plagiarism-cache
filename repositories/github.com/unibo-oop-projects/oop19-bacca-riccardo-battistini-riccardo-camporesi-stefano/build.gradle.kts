plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "5.2.0"
}
repositories {
    jcenter()
}

val javaFXModules = listOf(
    "base",
    "controls",
    "fxml",
    //"swing",
    "graphics"
)

val supportedPlatforms = listOf("linux", "mac", "win")

dependencies {

    implementation("org.apache.commons:commons-lang3:3.6")
    
    // Logging with logback and slf4j 
  	compile("org.slf4j:slf4j-api:1.7.30")
    compile("ch.qos.logback:logback-classic:1.2.3")
	compile("ch.qos.logback:logback-core:1.2.3")
	
	// JGraphT library for using graphs in Java
    compile("org.jgrapht:jgrapht-jdk1.5:0.7.3")
     
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

application {
    mainClassName = "application.Launcher"
}
