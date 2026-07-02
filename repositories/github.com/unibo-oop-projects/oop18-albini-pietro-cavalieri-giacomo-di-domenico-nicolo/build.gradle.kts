val gdxVersion: String by project

plugins {
    java
    application
}

repositories {
    jcenter()
    mavenLocal()
    mavenCentral()
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
}

buildscript {
    dependencies {
        classpath("com.badlogicgames.gdx:gdx-tools:1.9.8")
    }
}

dependencies {
    testImplementation("junit:junit:4.12")
    implementation("com.badlogicgames.gdx:gdx-freetype:$gdxVersion")
    implementation("com.badlogicgames.gdx:gdx-freetype-platform:$gdxVersion:natives-desktop")
    implementation("io.github.classgraph:classgraph:4.8.16")
    implementation("org.jooq:jool-java-8:0.9.14")
    implementation("com.badlogicgames.gdx:gdx:$gdxVersion")
    implementation("com.badlogicgames.gdx:gdx-backend-lwjgl3:$gdxVersion")
    implementation("com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-desktop")
}

application {
    mainClassName = "todo.launcher.Launcher"
}

tasks.jar {
    archiveName = "todo.jar"
    from(rootProject.file("assets").absolutePath) {
        into("assets")
    }
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })

    manifest {
        attributes["Main-Class"] = application.mainClassName
    }
}
